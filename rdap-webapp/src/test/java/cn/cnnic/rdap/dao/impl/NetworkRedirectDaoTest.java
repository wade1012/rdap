/*
 * Copyright (c) 2012 - 2015, Internet Corporation for Assigned Names and
 * Numbers (ICANN) and China Internet Network Information Center (CNNIC)
 *
 * All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions are met:
 *
 * * Redistributions of source code must retain the above copyright notice,
 *  this list of conditions and the following disclaimer.
 * * Redistributions in binary form must reproduce the above copyright notice,
 *  this list of conditions and the following disclaimer in the documentation
 *  and/or other materials provided with the distribution.
 * * Neither the name of the ICANN, CNNIC nor the names of its contributors may
 *  be used to endorse or promote products derived from this software without
 *  specific prior written permission.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS"
 * AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE
 * IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE
 * ARE DISCLAIMED. IN NO EVENT SHALL ICANN OR CNNIC BE LIABLE FOR ANY DIRECT,
 * INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES
 * (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR
 * SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER
 * CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT
 * LIABILITY, OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY
 * OUT OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH
 * DAMAGE.
 */
package cn.cnnic.rdap.dao.impl;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import cn.cnnic.rdap.BaseTest;
import cn.cnnic.rdap.bean.Network.IpVersion;
import cn.cnnic.rdap.bean.QueryParam;
import cn.cnnic.rdap.bean.RedirectResponse;
import cn.cnnic.rdap.controller.support.QueryParser;
import cn.cnnic.rdap.dao.RedirectDao;

import com.github.springtestdbunit.annotation.DatabaseSetup;
import com.github.springtestdbunit.annotation.DatabaseTearDown;

/**
 * Test for network redirect DAO.
 * 
 * @author jiashuo
 * 
 */
@SuppressWarnings("rawtypes")
public class NetworkRedirectDaoTest extends BaseTest {
    /**
     * domainRedirectDao.
     */
    @Autowired
    @Qualifier("networkRedirectDao")
    private RedirectDao redirectDao;

    /**
     * queryParser.
     */
    @Autowired
    private QueryParser queryParser;

    /**
     * test query exist v4.
     */
    @Test
    @DatabaseTearDown("teardown.xml")
    @DatabaseSetup("network-redirect.xml")
    public void testQueryV4() {
        // exist
        IpVersion versionV4 = IpVersion.V4;
        QueryParam queryParam =
                queryParser.parseIpQueryParam("1.0.0.0", 0, versionV4);
        RedirectResponse redirect = redirectDao.query(queryParam);
        assertNotNull(redirect);
        assertEquals("http://cnnic.cn/rdap", redirect.getUrl());
        queryParam = queryParser.parseIpQueryParam("1.1.0.0", 0, versionV4);
        redirect = redirectDao.query(queryParam);
        assertNotNull(redirect);
        assertEquals("http://cnnic.cn/rdap", redirect.getUrl());
        queryParam =
                queryParser.parseIpQueryParam("1.255.255.254", 0, versionV4);
        redirect = redirectDao.query(queryParam);
        assertNotNull(redirect);
        assertEquals("http://cnnic.cn/rdap", redirect.getUrl());
        queryParam =
                queryParser.parseIpQueryParam("1.255.255.255", 0, versionV4);
        redirect = redirectDao.query(queryParam);
        assertNotNull(redirect);
        assertEquals("http://cnnic.cn/rdap", redirect.getUrl());
        // non exist
        queryParam = queryParser.parseIpQueryParam("0.0.0.1", 0, versionV4);
        redirect = redirectDao.query(queryParam);
        assertNull(redirect);
        queryParam =
                queryParser.parseIpQueryParam("2.255.255.255", 0, versionV4);
        redirect = redirectDao.query(queryParam);
        assertNull(redirect);
    }

    /**
     * test query exist v4.
     */
    @Test
    @DatabaseTearDown("teardown.xml")
    @DatabaseSetup("network-redirect.xml")
    public void testQueryV6() {
        // exist
        IpVersion versionV6 = IpVersion.V6;
        QueryParam queryParam =
                queryParser.parseIpQueryParam("0:0:0:0:2001:6a8:0:1", 0,
                        versionV6);
        RedirectResponse redirect = redirectDao.query(queryParam);
        assertNotNull(redirect);
        assertEquals("http://cnnic.cn/rdap", redirect.getUrl());
        queryParam =
                queryParser.parseIpQueryParam("0:0:0:0:2002:6a8:0:1", 0,
                        versionV6);
        redirect = redirectDao.query(queryParam);
        assertNotNull(redirect);
        assertEquals("http://cnnic.cn/rdap", redirect.getUrl());
        queryParam =
                queryParser.parseIpQueryParam("0:db8:85a3:0:2001:6a8:0:2", 0,
                        versionV6);
        redirect = redirectDao.query(queryParam);
        assertNotNull(redirect);
        assertEquals("http://cnnic.cn/rdap", redirect.getUrl());
        queryParam =
                queryParser.parseIpQueryParam("2001:db8:85a3:0:2001:6a8:0:2",
                        0, versionV6);
        redirect = redirectDao.query(queryParam);
        assertNotNull(redirect);
        assertEquals("http://cnnic.cn/rdap", redirect.getUrl());
        // non exist
        queryParam =
                queryParser.parseIpQueryParam("0:0:0:0:2000:6a8:0:1", 0,
                        versionV6);
        redirect = redirectDao.query(queryParam);
        assertNull(redirect);
        queryParam =
                queryParser.parseIpQueryParam("2001:db8:85a3:0:2001:6a8:0:3",
                        0, versionV6);
        redirect = redirectDao.query(queryParam);
        assertNull(redirect);
    }

}
