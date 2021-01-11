package com.abhishek.seller.core.database;

public class DataConverter {

//
//    @TypeConverter
//    public ContactPersonModel toContactPersonModel(String data) {
//        if (data == null || TextUtils.isEmpty(data)) {
//            return (null);
//        }
//        Gson gson = new Gson();
//        Type type = new TypeToken<ContactPersonModel>() {
//        }.getType();
//        return gson.fromJson(data, type);
//    }
//
//
//    @TypeConverter
//    public String fromContactPersonModel(ContactPersonModel model) {
//        if (model == null) {
//            return (null);
//        }
//        Gson gson = new Gson();
//        Type type = new TypeToken<ContactPersonModel>() {
//        }.getType();
//        return gson.toJson(model, type);
//    }
//
//    @TypeConverter
//    public String fromHoleModel(List<HoleModel> list) {
//        if (list == null || list.isEmpty()) {
//            return (null);
//        }
//        Gson gson = new Gson();
//        Type type = new TypeToken<List<HoleModel>>() {
//        }.getType();
//        return gson.toJson(list, type);
//    }
//
//    @TypeConverter
//    public List<HoleModel> toHoleModel(String data) {
//        if (data == null || TextUtils.isEmpty(data)) {
//            return (null);
//        }
//        Gson gson = new Gson();
//        Type type = new TypeToken<List<HoleModel>>() {
//        }.getType();
//        return gson.fromJson(data, type);
//    }
//
//
//
//    @TypeConverter
//    public String fromOfferListModel(List<OfferListModel> list) {
//        if (list == null || list.isEmpty()) {
//            return (null);
//        }
//        Gson gson = new Gson();
//        Type type = new TypeToken<List<OfferListModel>>() {
//        }.getType();
//        return gson.toJson(list, type);
//    }
//
//    @TypeConverter
//    public List<OfferListModel> toOfferListModel(String data) {
//        if (data == null || TextUtils.isEmpty(data)) {
//            return (null);
//        }
//        Gson gson = new Gson();
//        Type type = new TypeToken<List<OfferListModel>>() {
//        }.getType();
//        return gson.fromJson(data, type);
//    }
//
//
//    @TypeConverter
//    public String fromCourseGalleryImageModelList(List<CourseGalleryImageModel> list) {
//        if (list == null || list.isEmpty()) {
//            return (null);
//        }
//        Gson gson = new Gson();
//        Type type = new TypeToken<List<CourseGalleryImageModel>>() {
//        }.getType();
//        return gson.toJson(list, type);
//    }
//
//    @TypeConverter
//    public List<CourseGalleryImageModel> toCourseGalleryImageModelList(String data) {
//        if (data == null || TextUtils.isEmpty(data)) {
//            return (null);
//        }
//        Gson gson = new Gson();
//        Type type = new TypeToken<List<CourseGalleryImageModel>>() {
//        }.getType();
//        return gson.fromJson(data, type);
//    }
//
//
//    @TypeConverter
//    public String fromGalleryImageList(List<String> list) {
//        if (list == null || list.isEmpty()) {
//            return (null);
//        }
//        Gson gson = new Gson();
//        Type type = new TypeToken<List<String>>() {
//        }.getType();
//        return gson.toJson(list, type);
//    }
//
//    @TypeConverter
//    public List<String> toGalleryImageList(String data) {
//        if (data == null || TextUtils.isEmpty(data)) {
//            return (null);
//        }
//        Gson gson = new Gson();
//        Type type = new TypeToken<List<String>>() {
//        }.getType();
//        return gson.fromJson(data, type);
//    }
//
//
//
//    @TypeConverter
//    public String fromOutletGalleryModelList(List<OutletGalleryModel> list) {
//        if (list == null || list.isEmpty()) {
//            return (null);
//        }
//        Gson gson = new Gson();
//        Type type = new TypeToken<List<OutletGalleryModel>>() {
//        }.getType();
//        return gson.toJson(list, type);
//    }
//
//    @TypeConverter
//    public List<OutletGalleryModel> toOutletGalleryModelList(String data) {
//        if (data == null || TextUtils.isEmpty(data)) {
//            return (null);
//        }
//        Gson gson = new Gson();
//        Type type = new TypeToken<List<OutletGalleryModel>>() {
//        }.getType();
//        return gson.fromJson(data, type);
//    }
//
//
//
//    @TypeConverter
//    public String fromDeliveryHourModelList(List<DeliveryHourModel> list) {
//        if (list == null || list.isEmpty()) {
//            return (null);
//        }
//        Gson gson = new Gson();
//        Type type = new TypeToken<List<DeliveryHourModel>>() {
//        }.getType();
//        return gson.toJson(list, type);
//    }
//
//    @TypeConverter
//    public List<DeliveryHourModel> toDeliveryHourModelList(String data) {
//        if (data == null || TextUtils.isEmpty(data)) {
//            return (null);
//        }
//        Gson gson = new Gson();
//        Type type = new TypeToken<List<DeliveryHourModel>>() {
//        }.getType();
//        return gson.fromJson(data, type);
//    }
//
//
//    @TypeConverter
//    public String fromBusinessHourModelList(List<BusinessHourModel> list) {
//        if (list == null || list.isEmpty()) {
//            return (null);
//        }
//        Gson gson = new Gson();
//        Type type = new TypeToken<List<BusinessHourModel>>() {
//        }.getType();
//        return gson.toJson(list, type);
//    }
//
//    @TypeConverter
//    public List<BusinessHourModel> toBusinessHourModelList(String data) {
//        if (data == null || TextUtils.isEmpty(data)) {
//            return (null);
//        }
//        Gson gson = new Gson();
//        Type type = new TypeToken<List<BusinessHourModel>>() {
//        }.getType();
//        return gson.fromJson(data, type);
//    }
//
//
//    @TypeConverter
//    public String fromCusineList(List<CuisineModel> list) {
//        if (list == null || list.isEmpty()) {
//            return (null);
//        }
//        Gson gson = new Gson();
//        Type type = new TypeToken<List<CuisineModel>>() {
//        }.getType();
//        return gson.toJson(list, type);
//    }
//
//    @TypeConverter
//    public List<CuisineModel> toCusineList(String data) {
//        if (data == null || TextUtils.isEmpty(data)) {
//            return (null);
//        }
//        Gson gson = new Gson();
//        Type type = new TypeToken<List<CuisineModel>>() {
//        }.getType();
//        return gson.fromJson(data, type);
//    }
//
//
//    @TypeConverter
//    public String fromAmenityList(List<AmenityModel> amenityList) {
//        if (amenityList == null || amenityList.isEmpty()) {
//            return (null);
//        }
//        Gson gson = new Gson();
//        Type type = new TypeToken<List<AmenityModel>>() {
//        }.getType();
//        return gson.toJson(amenityList, type);
//    }
//
//    @TypeConverter
//    public List<AmenityModel> toAmenityList(String amenityListString) {
//        if (amenityListString == null || TextUtils.isEmpty(amenityListString)) {
//            return (null);
//        }
//        Gson gson = new Gson();
//        Type type = new TypeToken<List<AmenityModel>>() {
//        }.getType();
//        return gson.fromJson(amenityListString, type);
//    }
//
//
//    @TypeConverter
//    public List<ProfileModel> toProfiles(String prfileListJson) {
//        if (prfileListJson == null || TextUtils.isEmpty(prfileListJson)) {
//            return (null);
//        }
//        Gson gson = new Gson();
//        Type type = new TypeToken<List<ProfileModel>>() {
//        }.getType();
//        return gson.fromJson(prfileListJson, type);
//    }
//
//    @TypeConverter
//    public String fromProfiles(List<ProfileModel> prfileList) {
//        if (prfileList == null) {
//            return (null);
//        }
//        Gson gson = new Gson();
//        Type type = new TypeToken<List<ProfileModel>>() {
//        }.getType();
//        return gson.toJson(prfileList, type);
//    }
//
//    @TypeConverter
//    public List<MaintenanceDay> toMaintenanceDay(String maintenanceDayListJson) {
//        if (maintenanceDayListJson == null || TextUtils.isEmpty(maintenanceDayListJson)) {
//            return (null);
//        }
//        Gson gson = new Gson();
//        Type type = new TypeToken<List<MaintenanceDay>>() {
//        }.getType();
//        return gson.fromJson(maintenanceDayListJson, type);
//    }
//
//    @TypeConverter
//    public String fromMaintenanceDay(List<MaintenanceDay> maintenanceDayList) {
//        if (maintenanceDayList == null) {
//            return (null);
//        }
//        Gson gson = new Gson();
//        Type type = new TypeToken<List<MaintenanceDay>>() {
//        }.getType();
//        return gson.toJson(maintenanceDayList, type);
//    }

}