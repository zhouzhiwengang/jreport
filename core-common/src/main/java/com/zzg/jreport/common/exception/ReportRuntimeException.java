package com.zzg.jreport.common.exception;

import org.apache.commons.lang3.StringUtils;

@SuppressWarnings("serial")
public class ReportRuntimeException extends RuntimeException {
	 /** The code. */
    private String code;

    /** The params. */
    private String[] params;

    /**
     * Gets the code.
     * 
     * @return the code
     */
    public String getCode() {
        return code;
    }

    /**
     * Sets the code.
     * 
     * @param code
     *            the new code
     */
    public void setCode(String code) {
        this.code = code;
    }

    /**
     * Gets the params.
     * 
     * @return the params
     */
    public String[] getParams() {
        return params;
    }

    /**
     * Sets the params.
     * 
     * @param params
     *            the new params
     */
    public void setParams(String[] params) {
        this.params = params;
    }

    /**
     * Instantiates a new base runtime exception.
     */
    public ReportRuntimeException() {
        super();
    }

    /**
     * Instantiates a new base runtime exception.
     * 
     * @param message
     *            the message
     * @param e
     *            the e
     */
    public ReportRuntimeException(String message, Exception e) {
        super(message, e);
    }

    /**
     * Instantiates a new base runtime exception.
     * 
     * @param message
     *            the message
     */
    public ReportRuntimeException(String message) {
        super(message);
    }

    /**
     * Instantiates a new base runtime exception.
     * 
     * @param e
     *            the e
     */
    public ReportRuntimeException(Exception e) {
        super(e);
    }

    /**
     * Instantiates a new base runtime exception.
     * 
     * @param code
     *            the code
     * @param params
     *            the params
     */
    public ReportRuntimeException(String code, String[] params) {
        super(code);
        this.setCode(code);
        this.setParams(params);
    }

    /**
     * Instantiates a new base runtime exception.
     * 
     * @param code
     *            the code
     * @param params
     *            the params
     * @param e
     *            the e
     */
    public ReportRuntimeException(String code, String[] params, Exception e) {
        super(e);
        this.setCode(code);
        this.setParams(params);
    }

    /*
     * (non-Javadoc)
     * 
     * @see java.lang.Throwable#getMessage()
     */
    @Override
    public String getMessage() {
        if (code == null || code.length() == 0) {
            return super.getMessage();
        }
        String paramsStr = "NA";
        if (params != null) {
            paramsStr = StringUtils.join(params, ",");
        }
        String codeMessage = "code:" + code + ";parameters:" + paramsStr;
        return codeMessage;
    }

    /*
     * (non-Javadoc)
     * 
     * @see java.lang.Throwable#toString()
     */
    @Override
    public String toString() {
        String s = getClass().getName();
        String message = this.getMessage();
        return (message != null) ? (s + ": " + message) : s;
    }
}
