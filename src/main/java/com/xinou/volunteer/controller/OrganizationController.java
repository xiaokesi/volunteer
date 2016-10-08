package com.xinou.volunteer.controller;

import com.jfinal.core.Controller;
import com.xinou.volunteer.dao.Organization;

import java.util.List;

/**
 * 组织机构操作
 * Created by shizhida on 16/1/13.
 */
public class OrganizationController extends Controller {

    public void getOrganization(){
        List<Organization> organizations = Organization.dao.find("select * from organization");
        setAttr("activitys", organizations);
        render("/resources/organization.html");
    }

}
