package com.ipl.people.v2.dao;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.apache.commons.codec.language.DoubleMetaphone;
import org.apache.commons.lang.StringUtils;
import org.hibernate.Query;
import org.hibernate.SessionFactory;

import com.google.common.base.Optional;
import com.sun.jersey.api.NotFoundException;

import io.dropwizard.hibernate.AbstractDAO;

import com.ipl.people.v2.core.FindPersonV2;

/**
 * DAO class providing find person functionality for version2(latest).
 *  
 * @author Asha
 *
 */
public class FindPersonDAO extends AbstractDAO<FindPersonV2> {
    public FindPersonDAO(final SessionFactory factory) {
        super(factory);
    }

    public static int recordsLimit = 20;
    public static int recordsOffset = 0;

    /**
     * API to find persons with the possible matches of all the inputs provided by the user.
     * 
     * @param name
     * @param surname
     * @param ageStr
     * @param dateOfBirth
     * @param pncId
     * @param niNumber
     * @param driverNo
     * @param croNo
     * @param passportNo
     * @param limit
     * @param offset
     * @return List of matched persons
     * @throws Exception
     */
    public List<FindPersonV2> findPerson(final Optional<String> name, final Optional<String> surname, final Optional<String> ageStr, final Optional<String> dateOfBirth, final Optional<String> pncId,
        final Optional<String> niNumber, final Optional<String> driverNo, final Optional<String> croNo, final Optional<String> passportNo, final Optional<Integer> limit, final Optional<Integer> offset) throws Exception {

        DoubleMetaphone metaphone = new DoubleMetaphone();
        StringBuilder exactMatch = new StringBuilder("");
        StringBuilder partialMatch = new StringBuilder(" ( ");
        String sql1 = "(SELECT distinct p.person_uid, p.gender, n.Name_UID, n.person_uid, n.Name_Type,n.Forename1, n.Surname FROM homeofficev2.person p LEFT JOIN homeofficev2.name_alias n on p.person_uid = n.person_uid LEFT JOIN homeofficev2.alias_dob d ON p.person_uid = d.person_uid LEFT JOIN homeofficev2.identifier i ON p.person_uid = i.person_uid where n.Name_Type='P' and";
        String sql2 = " UNION (SELECT distinct p.person_uid, p.gender, n.Name_UID, n.person_uid, n.Name_Type,n.Forename1, n.Surname FROM homeofficev2.person p LEFT JOIN homeofficev2.name_alias n on p.person_uid = n.person_uid LEFT JOIN homeofficev2.alias_dob d ON p.person_uid = d.person_uid LEFT JOIN homeofficev2.identifier i ON p.person_uid = i.person_uid where n.Name_Type='P' and ";

        if (pncId != null && pncId.isPresent() && pncId.get().length() > 0) {
            exactMatch = exactMatch.append(" p.PNC_ID ='" + pncId.get() + "' ");
            ;
            partialMatch = partialMatch.append(" p.PNC_ID ='" + pncId.get() + "' ");
        }

        if (name != null && name.isPresent() && name.get().length() > 0) {
            if (StringUtils.isNotBlank(exactMatch.toString())) {
                exactMatch = exactMatch.append(" and ");
                partialMatch = partialMatch.append(" or ");
            }
            String metaphoneForename = metaphone.doubleMetaphone(name.get());
            String nameStr = " (n.Forename1_Metaphone = '" + metaphoneForename + "' or n.Forename2_Metaphone = '" + metaphoneForename + "' or n.Forename3_Metaphone = '" + metaphoneForename + "' or n.Forename4_Metaphone = '" + metaphoneForename + "') ";
            exactMatch = exactMatch.append(nameStr);
            partialMatch = partialMatch.append(nameStr);
        }

        if (surname != null && surname.isPresent() && surname.get().length() > 0) {
            if (StringUtils.isNotBlank(exactMatch.toString())) {
                exactMatch = exactMatch.append(" and ");
                partialMatch = partialMatch.append(" or ");
            }
            String metaphoneSurname = metaphone.doubleMetaphone(surname.get());
            exactMatch = exactMatch.append(" n.surname_Metaphone = '" + metaphoneSurname + "' ");
            partialMatch = partialMatch.append(" n.surname_Metaphone = '" + metaphoneSurname + "' ");
        }

        if (ageStr != null && ageStr.isPresent() && ageStr.get().length() > 0) {
            if (StringUtils.isNotBlank(exactMatch.toString())) {
                exactMatch = exactMatch.append(" and ");
                partialMatch = partialMatch.append(" or ");
            }

            String dateRange = getAgeRange(ageStr);
            exactMatch = exactMatch.append(dateRange);
            partialMatch = partialMatch.append(dateRange);
        }

        if (dateOfBirth != null && dateOfBirth.isPresent() && dateOfBirth.get().length() > 0) {
            if (StringUtils.isNotBlank(exactMatch.toString())) {
                exactMatch = exactMatch.append(" and ");
                partialMatch = partialMatch.append(" or ");
            }
            String dateRange = getDateOfBirthRange(dateOfBirth);
            exactMatch = exactMatch.append(dateRange);
            ;
            partialMatch = partialMatch.append(dateRange);
        }

        if (niNumber != null && niNumber.isPresent() && niNumber.get().length() > 0) {
            if (StringUtils.isNotBlank(exactMatch.toString())) {
                exactMatch = exactMatch.append(" and ");
                partialMatch = partialMatch.append(" or ");
            }
            exactMatch = exactMatch.append(" i.Identifier = '" + niNumber.get() + "' and i.Identifier_Type = 'N' ");
            partialMatch = partialMatch.append("( i.Identifier = '" + niNumber.get() + "' and i.Identifier_Type = 'N' )");
        }

        if (driverNo != null && driverNo.isPresent() && driverNo.get().length() > 0) {
            if (StringUtils.isNotBlank(exactMatch.toString())) {
                exactMatch = exactMatch.append(" and ");
                partialMatch = partialMatch.append(" or ");
            }
            exactMatch = exactMatch.append(" i.Identifier = '" + driverNo.get() + "' and i.Identifier_Type = 'D' ");
            partialMatch = partialMatch.append("( i.Identifier = '" + driverNo.get() + "' and i.Identifier_Type = 'D' )");
        }
        if (croNo != null && croNo.isPresent() && croNo.get().length() > 0) {
            if (StringUtils.isNotBlank(exactMatch.toString())) {
                exactMatch = exactMatch.append(" and ");
                partialMatch = partialMatch.append(" or ");
            }
            exactMatch = exactMatch.append(" i.Identifier = '" + croNo.get() + "' and i.Identifier_Type = 'C' ");
            partialMatch = partialMatch.append("( i.Identifier = '" + croNo.get() + "' and i.Identifier_Type = 'C' )");
        }
        if (passportNo != null && passportNo.isPresent() && passportNo.get().length() > 0) {
            if (StringUtils.isNotBlank(exactMatch.toString())) {
                exactMatch = exactMatch.append(" and ");
                partialMatch = partialMatch.append(" or ");
            }
            exactMatch = exactMatch.append(" i.Identifier = '" + passportNo.get() + "' and i.Identifier_Type = 'P' ");
            partialMatch = partialMatch.append("( i.Identifier = '" + passportNo.get() + "' and i.Identifier_Type = 'P' )");
        }

        exactMatch = exactMatch.append(" order by p.Person_UID ) ");
        ;
        partialMatch = partialMatch.append(" ) order by p.Person_UID ) ");

        Query findPersons = currentSession().createSQLQuery(sql1 + exactMatch.toString() + sql2 + partialMatch.toString()).addEntity(FindPersonV2.class);

        if (offset != null && offset.isPresent() && offset.get() > 0)
        {
            findPersons.setFirstResult(offset.get());
        } else {
            findPersons.setFirstResult(recordsOffset);
        }
        if (limit != null && limit.isPresent() && limit.get() > 0)
        {
            findPersons.setMaxResults(limit.get());
        } else {
            findPersons.setMaxResults(recordsLimit);
        }

        return list(findPersons);

    }

    private String getAgeRange(final Optional<String> ageStr) {

        String age = ageStr.get();
        int hyphenIndex = age.indexOf('-');

        if (hyphenIndex == -1) {
            int ageInt = 0;
            try {
                ageInt = Integer.parseInt(age);
            } catch (NumberFormatException e) {
                throw new NotFoundException("Please provide age in the format number or number-number.");
            }

            int currentYear = Calendar.getInstance().get(Calendar.YEAR);
            Calendar c1 = Calendar.getInstance();
            c1.set(currentYear - ageInt, 0, 1);
            Date dateOfBirthFrom = c1.getTime();
            DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
            StringBuilder dateRange = new StringBuilder("((n.date_of_birth >= '" + formatter.format(dateOfBirthFrom) + "' AND n.date_of_birth <= ");
            Calendar c2 = Calendar.getInstance();
            c2.set(currentYear - ageInt, 11, 31);
            Date dateOfBirthTo = c2.getTime();
            dateRange = dateRange.append("'" + formatter.format(dateOfBirthTo) + "') or (d.date_of_birth >= '"
                + formatter.format(dateOfBirthFrom) + "' AND d.date_of_birth <= '" + formatter.format(dateOfBirthTo) + "'))");
            return dateRange.toString();

        } else {
            int age1 = 0;
            int age2 = 0;
            try {
                age1 = Integer.parseInt(age.substring(0, hyphenIndex));
                age2 = Integer.parseInt(age.substring(hyphenIndex + 1, age.length()));
                if (age2 > age1) {
                    int temp = age1;
                    age1 = age2;
                    age2 = temp;
                }
            } catch (NumberFormatException e) {
                throw new NotFoundException("Please provide age in the format number or number-number.");
            }
            int currentYear = Calendar.getInstance().get(Calendar.YEAR);
            Calendar c1 = Calendar.getInstance();
            c1.set(currentYear - age1, 0, 1);
            Date dateOfBirthFrom = c1.getTime();
            DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
            StringBuilder dateRange = new StringBuilder(" ((n.date_of_birth >= '" + formatter.format(dateOfBirthFrom) + "' AND n.date_of_birth <= ");
            Calendar c2 = Calendar.getInstance();
            c2.set(currentYear - age2, 11, 31);
            Date dateOfBirthTo = c2.getTime();
            dateRange = dateRange.append("'" + formatter.format(dateOfBirthTo) + "') or (d.date_of_birth >= '"
                + formatter.format(dateOfBirthFrom) + "' AND d.date_of_birth <= '" + formatter.format(dateOfBirthTo) + "'))");
            return dateRange.toString();

        }
    }

    private String getDateOfBirthRange(final Optional<String> dateOfBirth) {
        String[] dates = dateOfBirth.get().split("-");
        String dateFormat = "";
        if (dates.length == 3) {
            dateFormat = "yyyy-MM-dd";
        } else if (dates.length == 2) {
            dateFormat = "yyyy-MM";
        } else {
            dateFormat = "yyyy";
        }
        Date date;
        try {
            DateFormat formatter = new SimpleDateFormat(dateFormat);
            date = formatter.parse(dateOfBirth.get());
        } catch (ParseException e) {
            throw new NotFoundException("Please provide Date of birth in the format yyyy-MM-dd.");
        }
        if (dates.length == 3) {
            String dateRange = " (n.date_of_birth = '" + dateOfBirth.get() + "' or d.date_of_birth = '" + dateOfBirth.get() + "')";
            return dateRange;
        } else if (dates.length == 2) {
            Calendar c = Calendar.getInstance();
            c.setTime(date);
            String dateRange = " ((n.date_of_birth >= '" + dateOfBirth.get() + "-" + c.getActualMinimum(Calendar.DAY_OF_MONTH)
                + "' AND n.date_of_birth <= '" + dateOfBirth.get() + "-" + c.getActualMaximum(Calendar.DAY_OF_MONTH) + "') or (d.date_of_birth >= '"
                + dateOfBirth.get() + "-" + c.getActualMinimum(Calendar.DAY_OF_MONTH) + "' AND d.date_of_birth <= '" + dateOfBirth.get() + "-" + c.getActualMaximum(Calendar.DAY_OF_MONTH) + "'))";

            return dateRange;
        } else {
            String dateRange = " ((n.date_of_birth >= '" + dateOfBirth.get() + "-01-01"
                + "' AND n.date_of_birth <= '" + dateOfBirth.get() + "-12-31" + "') or  (d.date_of_birth >= '"
                + dateOfBirth.get() + "-01-01" + "' AND d.date_of_birth <= '" + dateOfBirth.get() + "-12-31" + "'))";
            return dateRange;
        }
    }
}
