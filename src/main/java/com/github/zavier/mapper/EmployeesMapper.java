package com.github.zavier.mapper;

import com.github.zavier.model.Employees;
import com.github.zavier.model.EmployeesExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface EmployeesMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table employees
     *
     * @mbg.generated Wed Sep 13 09:00:48 CST 2023
     */
    long countByExample(EmployeesExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table employees
     *
     * @mbg.generated Wed Sep 13 09:00:48 CST 2023
     */
    int deleteByExample(EmployeesExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table employees
     *
     * @mbg.generated Wed Sep 13 09:00:48 CST 2023
     */
    int deleteByPrimaryKey(Integer empNo);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table employees
     *
     * @mbg.generated Wed Sep 13 09:00:48 CST 2023
     */
    int insert(Employees row);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table employees
     *
     * @mbg.generated Wed Sep 13 09:00:48 CST 2023
     */
    int insertSelective(Employees row);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table employees
     *
     * @mbg.generated Wed Sep 13 09:00:48 CST 2023
     */
    List<Employees> selectByExample(EmployeesExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table employees
     *
     * @mbg.generated Wed Sep 13 09:00:48 CST 2023
     */
    Employees selectByPrimaryKey(Integer empNo);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table employees
     *
     * @mbg.generated Wed Sep 13 09:00:48 CST 2023
     */
    int updateByExampleSelective(@Param("row") Employees row, @Param("example") EmployeesExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table employees
     *
     * @mbg.generated Wed Sep 13 09:00:48 CST 2023
     */
    int updateByExample(@Param("row") Employees row, @Param("example") EmployeesExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table employees
     *
     * @mbg.generated Wed Sep 13 09:00:48 CST 2023
     */
    int updateByPrimaryKeySelective(Employees row);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table employees
     *
     * @mbg.generated Wed Sep 13 09:00:48 CST 2023
     */
    int updateByPrimaryKey(Employees row);
}