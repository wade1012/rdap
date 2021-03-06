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
package cn.cnnic.rdap.bean;

/**
 * query and search type.
 * 
 * @author jiashuo
 * 
 */
public enum QueryType {
    /**
     * query type.
     * 
     * @param domain
     *            a kind of type.
     * @param entity
     *            a kind of type.
     * @param nameServer
     *            a kind of type.
     * @param autnum
     *            a kind of type.
     */
    DOMAIN("domain"), ENTITY("entity"), NAMESERVER("nameServer"), AUTNUM(
    /**
     * @param help a kind of type.
     * 
     * @param ip
     *            a kind of type.
     * @param searchDomain
     *            a kind of type.
     */
    "autnum"), HELP("help"), IP("ip"), SEARCHDOMAIN("searchDomain"),
    /**
    * @param searchEntity
    *            a kind of type.
    * @param searchNameserver
    *            a kind of type.
    */
    SEARCHENTITY("searchEntity"), SEARCHNAMESERVER("searchNameserver");
    /**
     * name of query type.
     */
    private String name;

    /**
     * default construction.
     * 
     * @param name
     *            query type name
     */
    private QueryType(String name) {
        this.name = name;
    }

    /**
     * get query type be name.
     * 
     * @param name
     *            :query type name
     * @return query type if name is valid, null if not
     */
    public static QueryType getQueryType(String name) {
        QueryType[] queryTypes = QueryType.values();
        for (QueryType joinType : queryTypes) {
            if (joinType.getName().equals(name)) {
                return joinType;
            }
        }
        return null;
    }

    /**
     * get name of query.
     * 
     * @return name string
     */
    public String getName() {
        return name;
    }

    /**
     * set name of query.
     * 
     * @param name
     *            string to set.
     */
    public void setName(String name) {
        this.name = name;
    }
}
