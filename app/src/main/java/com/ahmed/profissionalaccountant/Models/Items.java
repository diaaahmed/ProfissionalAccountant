
package com.ahmed.profissionalaccountant.Models;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;


public class Items implements Serializable {

    @SerializedName("Active")
    private Boolean mActive;
    @SerializedName("CategoryID")
    private Long mCategoryID;
    @SerializedName("Code")
    private String mCode;
    @SerializedName("ID")
    private Integer mID;
    @SerializedName("IsExpired")
    private Boolean mIsExpired;
    @SerializedName("IsTax")
    private Boolean mIsTax;
    @SerializedName("IsWeight")
    private Boolean mIsWeight;
    @SerializedName("Location")
    private String mLocation;
    @SerializedName("NameArab")
    private String mNameArab;
    @SerializedName("NameEng")
    private String mNameEng;
    @SerializedName("Note2")
    private String mNote2;
    @SerializedName("PhotoItem")
    private String mPhotoItem;
    @SerializedName("SectionID")
    private Integer mSectionID;
    @SerializedName("TypeItem_id")
    private Long mTypeItemId;
    @SerializedName("UnderCost")
    private Boolean mUnderCost;

    @Override
    public String toString() {
        return  mNameArab
               ;
    }

    public Boolean getActive() {
        return mActive;
    }

    public void setActive(Boolean active) {
        mActive = active;
    }

    public Long getCategoryID() {
        return mCategoryID;
    }

    public void setCategoryID(Long categoryID) {
        mCategoryID = categoryID;
    }

    public String getCode() {
        return mCode;
    }

    public void setCode(String code) {
        mCode = code;
    }

    public Integer getID() {
        return mID;
    }

    public void setID(Integer iD) {
        mID = iD;
    }

    public Boolean getIsExpired() {
        return mIsExpired;
    }

    public void setIsExpired(Boolean isExpired) {
        mIsExpired = isExpired;
    }

    public Boolean getIsTax() {
        return mIsTax;
    }

    public void setIsTax(Boolean isTax) {
        mIsTax = isTax;
    }

    public Boolean getIsWeight() {
        return mIsWeight;
    }

    public void setIsWeight(Boolean isWeight) {
        mIsWeight = isWeight;
    }

    public String getLocation() {
        return mLocation;
    }

    public void setLocation(String location) {
        mLocation = location;
    }

    public String getNameArab() {
        return mNameArab;
    }

    public void setNameArab(String nameArab) {
        mNameArab = nameArab;
    }

    public Object getNameEng() {
        return mNameEng;
    }

    public void setNameEng(String nameEng) {
        mNameEng = nameEng;
    }

    public String getNote2() {
        return mNote2;
    }

    public void setNote2(String note2) {
        mNote2 = note2;
    }

    public String getPhotoItem() {
        return mPhotoItem;
    }

    public void setPhotoItem(String photoItem) {
        mPhotoItem = photoItem;
    }

    public Integer getSectionID() {
        return mSectionID;
    }

    public void setSectionID(Integer sectionID) {
        mSectionID = sectionID;
    }

    public Long getTypeItemId() {
        return mTypeItemId;
    }

    public void setTypeItemId(Long typeItemId) {
        mTypeItemId = typeItemId;
    }

    public Boolean getUnderCost() {
        return mUnderCost;
    }

    public void setUnderCost(Boolean underCost) {
        mUnderCost = underCost;
    }

}
