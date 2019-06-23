package com.xdorm.pojo;

import java.util.ArrayList;
import java.util.List;

public class TbRoomExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public TbRoomExample() {
        oredCriteria = new ArrayList<Criteria>();
    }

    public void setOrderByClause(String orderByClause) {
        this.orderByClause = orderByClause;
    }

    public String getOrderByClause() {
        return orderByClause;
    }

    public void setDistinct(boolean distinct) {
        this.distinct = distinct;
    }

    public boolean isDistinct() {
        return distinct;
    }

    public List<Criteria> getOredCriteria() {
        return oredCriteria;
    }

    public void or(Criteria criteria) {
        oredCriteria.add(criteria);
    }

    public Criteria or() {
        Criteria criteria = createCriteriaInternal();
        oredCriteria.add(criteria);
        return criteria;
    }

    public Criteria createCriteria() {
        Criteria criteria = createCriteriaInternal();
        if (oredCriteria.size() == 0) {
            oredCriteria.add(criteria);
        }
        return criteria;
    }

    protected Criteria createCriteriaInternal() {
        Criteria criteria = new Criteria();
        return criteria;
    }

    public void clear() {
        oredCriteria.clear();
        orderByClause = null;
        distinct = false;
    }

    protected abstract static class GeneratedCriteria {
        protected List<Criterion> criteria;

        protected GeneratedCriteria() {
            super();
            criteria = new ArrayList<Criterion>();
        }

        public boolean isValid() {
            return criteria.size() > 0;
        }

        public List<Criterion> getAllCriteria() {
            return criteria;
        }

        public List<Criterion> getCriteria() {
            return criteria;
        }

        protected void addCriterion(String condition) {
            if (condition == null) {
                throw new RuntimeException("Value for condition cannot be null");
            }
            criteria.add(new Criterion(condition));
        }

        protected void addCriterion(String condition, Object value, String property) {
            if (value == null) {
                throw new RuntimeException("Value for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value));
        }

        protected void addCriterion(String condition, Object value1, Object value2, String property) {
            if (value1 == null || value2 == null) {
                throw new RuntimeException("Between values for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value1, value2));
        }

        public Criteria andRoomIdIsNull() {
            addCriterion("room_id is null");
            return (Criteria) this;
        }

        public Criteria andRoomIdIsNotNull() {
            addCriterion("room_id is not null");
            return (Criteria) this;
        }

        public Criteria andRoomIdEqualTo(Long value) {
            addCriterion("room_id =", value, "roomId");
            return (Criteria) this;
        }

        public Criteria andRoomIdNotEqualTo(Long value) {
            addCriterion("room_id <>", value, "roomId");
            return (Criteria) this;
        }

        public Criteria andRoomIdGreaterThan(Long value) {
            addCriterion("room_id >", value, "roomId");
            return (Criteria) this;
        }

        public Criteria andRoomIdGreaterThanOrEqualTo(Long value) {
            addCriterion("room_id >=", value, "roomId");
            return (Criteria) this;
        }

        public Criteria andRoomIdLessThan(Long value) {
            addCriterion("room_id <", value, "roomId");
            return (Criteria) this;
        }

        public Criteria andRoomIdLessThanOrEqualTo(Long value) {
            addCriterion("room_id <=", value, "roomId");
            return (Criteria) this;
        }

        public Criteria andRoomIdIn(List<Long> values) {
            addCriterion("room_id in", values, "roomId");
            return (Criteria) this;
        }

        public Criteria andRoomIdNotIn(List<Long> values) {
            addCriterion("room_id not in", values, "roomId");
            return (Criteria) this;
        }

        public Criteria andRoomIdBetween(Long value1, Long value2) {
            addCriterion("room_id between", value1, value2, "roomId");
            return (Criteria) this;
        }

        public Criteria andRoomIdNotBetween(Long value1, Long value2) {
            addCriterion("room_id not between", value1, value2, "roomId");
            return (Criteria) this;
        }

        public Criteria andDormIdIsNull() {
            addCriterion("dorm_id is null");
            return (Criteria) this;
        }

        public Criteria andDormIdIsNotNull() {
            addCriterion("dorm_id is not null");
            return (Criteria) this;
        }

        public Criteria andDormIdEqualTo(Long value) {
            addCriterion("dorm_id =", value, "dormId");
            return (Criteria) this;
        }

        public Criteria andDormIdNotEqualTo(Long value) {
            addCriterion("dorm_id <>", value, "dormId");
            return (Criteria) this;
        }

        public Criteria andDormIdGreaterThan(Long value) {
            addCriterion("dorm_id >", value, "dormId");
            return (Criteria) this;
        }

        public Criteria andDormIdGreaterThanOrEqualTo(Long value) {
            addCriterion("dorm_id >=", value, "dormId");
            return (Criteria) this;
        }

        public Criteria andDormIdLessThan(Long value) {
            addCriterion("dorm_id <", value, "dormId");
            return (Criteria) this;
        }

        public Criteria andDormIdLessThanOrEqualTo(Long value) {
            addCriterion("dorm_id <=", value, "dormId");
            return (Criteria) this;
        }

        public Criteria andDormIdIn(List<Long> values) {
            addCriterion("dorm_id in", values, "dormId");
            return (Criteria) this;
        }

        public Criteria andDormIdNotIn(List<Long> values) {
            addCriterion("dorm_id not in", values, "dormId");
            return (Criteria) this;
        }

        public Criteria andDormIdBetween(Long value1, Long value2) {
            addCriterion("dorm_id between", value1, value2, "dormId");
            return (Criteria) this;
        }

        public Criteria andDormIdNotBetween(Long value1, Long value2) {
            addCriterion("dorm_id not between", value1, value2, "dormId");
            return (Criteria) this;
        }

        public Criteria andRoomNameIsNull() {
            addCriterion("room_name is null");
            return (Criteria) this;
        }

        public Criteria andRoomNameIsNotNull() {
            addCriterion("room_name is not null");
            return (Criteria) this;
        }

        public Criteria andRoomNameEqualTo(String value) {
            addCriterion("room_name =", value, "roomName");
            return (Criteria) this;
        }

        public Criteria andRoomNameNotEqualTo(String value) {
            addCriterion("room_name <>", value, "roomName");
            return (Criteria) this;
        }

        public Criteria andRoomNameGreaterThan(String value) {
            addCriterion("room_name >", value, "roomName");
            return (Criteria) this;
        }

        public Criteria andRoomNameGreaterThanOrEqualTo(String value) {
            addCriterion("room_name >=", value, "roomName");
            return (Criteria) this;
        }

        public Criteria andRoomNameLessThan(String value) {
            addCriterion("room_name <", value, "roomName");
            return (Criteria) this;
        }

        public Criteria andRoomNameLessThanOrEqualTo(String value) {
            addCriterion("room_name <=", value, "roomName");
            return (Criteria) this;
        }

        public Criteria andRoomNameLike(String value) {
            addCriterion("room_name like", value, "roomName");
            return (Criteria) this;
        }

        public Criteria andRoomNameNotLike(String value) {
            addCriterion("room_name not like", value, "roomName");
            return (Criteria) this;
        }

        public Criteria andRoomNameIn(List<String> values) {
            addCriterion("room_name in", values, "roomName");
            return (Criteria) this;
        }

        public Criteria andRoomNameNotIn(List<String> values) {
            addCriterion("room_name not in", values, "roomName");
            return (Criteria) this;
        }

        public Criteria andRoomNameBetween(String value1, String value2) {
            addCriterion("room_name between", value1, value2, "roomName");
            return (Criteria) this;
        }

        public Criteria andRoomNameNotBetween(String value1, String value2) {
            addCriterion("room_name not between", value1, value2, "roomName");
            return (Criteria) this;
        }

        public Criteria andBedCountIsNull() {
            addCriterion("bed_count is null");
            return (Criteria) this;
        }

        public Criteria andBedCountIsNotNull() {
            addCriterion("bed_count is not null");
            return (Criteria) this;
        }

        public Criteria andBedCountEqualTo(Integer value) {
            addCriterion("bed_count =", value, "bedCount");
            return (Criteria) this;
        }

        public Criteria andBedCountNotEqualTo(Integer value) {
            addCriterion("bed_count <>", value, "bedCount");
            return (Criteria) this;
        }

        public Criteria andBedCountGreaterThan(Integer value) {
            addCriterion("bed_count >", value, "bedCount");
            return (Criteria) this;
        }

        public Criteria andBedCountGreaterThanOrEqualTo(Integer value) {
            addCriterion("bed_count >=", value, "bedCount");
            return (Criteria) this;
        }

        public Criteria andBedCountLessThan(Integer value) {
            addCriterion("bed_count <", value, "bedCount");
            return (Criteria) this;
        }

        public Criteria andBedCountLessThanOrEqualTo(Integer value) {
            addCriterion("bed_count <=", value, "bedCount");
            return (Criteria) this;
        }

        public Criteria andBedCountIn(List<Integer> values) {
            addCriterion("bed_count in", values, "bedCount");
            return (Criteria) this;
        }

        public Criteria andBedCountNotIn(List<Integer> values) {
            addCriterion("bed_count not in", values, "bedCount");
            return (Criteria) this;
        }

        public Criteria andBedCountBetween(Integer value1, Integer value2) {
            addCriterion("bed_count between", value1, value2, "bedCount");
            return (Criteria) this;
        }

        public Criteria andBedCountNotBetween(Integer value1, Integer value2) {
            addCriterion("bed_count not between", value1, value2, "bedCount");
            return (Criteria) this;
        }

        public Criteria andFloorIsNull() {
            addCriterion("floor is null");
            return (Criteria) this;
        }

        public Criteria andFloorIsNotNull() {
            addCriterion("floor is not null");
            return (Criteria) this;
        }

        public Criteria andFloorEqualTo(Integer value) {
            addCriterion("floor =", value, "floor");
            return (Criteria) this;
        }

        public Criteria andFloorNotEqualTo(Integer value) {
            addCriterion("floor <>", value, "floor");
            return (Criteria) this;
        }

        public Criteria andFloorGreaterThan(Integer value) {
            addCriterion("floor >", value, "floor");
            return (Criteria) this;
        }

        public Criteria andFloorGreaterThanOrEqualTo(Integer value) {
            addCriterion("floor >=", value, "floor");
            return (Criteria) this;
        }

        public Criteria andFloorLessThan(Integer value) {
            addCriterion("floor <", value, "floor");
            return (Criteria) this;
        }

        public Criteria andFloorLessThanOrEqualTo(Integer value) {
            addCriterion("floor <=", value, "floor");
            return (Criteria) this;
        }

        public Criteria andFloorIn(List<Integer> values) {
            addCriterion("floor in", values, "floor");
            return (Criteria) this;
        }

        public Criteria andFloorNotIn(List<Integer> values) {
            addCriterion("floor not in", values, "floor");
            return (Criteria) this;
        }

        public Criteria andFloorBetween(Integer value1, Integer value2) {
            addCriterion("floor between", value1, value2, "floor");
            return (Criteria) this;
        }

        public Criteria andFloorNotBetween(Integer value1, Integer value2) {
            addCriterion("floor not between", value1, value2, "floor");
            return (Criteria) this;
        }

        public Criteria andMemberTotalIsNull() {
            addCriterion("member_total is null");
            return (Criteria) this;
        }

        public Criteria andMemberTotalIsNotNull() {
            addCriterion("member_total is not null");
            return (Criteria) this;
        }

        public Criteria andMemberTotalEqualTo(Integer value) {
            addCriterion("member_total =", value, "memberTotal");
            return (Criteria) this;
        }

        public Criteria andMemberTotalNotEqualTo(Integer value) {
            addCriterion("member_total <>", value, "memberTotal");
            return (Criteria) this;
        }

        public Criteria andMemberTotalGreaterThan(Integer value) {
            addCriterion("member_total >", value, "memberTotal");
            return (Criteria) this;
        }

        public Criteria andMemberTotalGreaterThanOrEqualTo(Integer value) {
            addCriterion("member_total >=", value, "memberTotal");
            return (Criteria) this;
        }

        public Criteria andMemberTotalLessThan(Integer value) {
            addCriterion("member_total <", value, "memberTotal");
            return (Criteria) this;
        }

        public Criteria andMemberTotalLessThanOrEqualTo(Integer value) {
            addCriterion("member_total <=", value, "memberTotal");
            return (Criteria) this;
        }

        public Criteria andMemberTotalIn(List<Integer> values) {
            addCriterion("member_total in", values, "memberTotal");
            return (Criteria) this;
        }

        public Criteria andMemberTotalNotIn(List<Integer> values) {
            addCriterion("member_total not in", values, "memberTotal");
            return (Criteria) this;
        }

        public Criteria andMemberTotalBetween(Integer value1, Integer value2) {
            addCriterion("member_total between", value1, value2, "memberTotal");
            return (Criteria) this;
        }

        public Criteria andMemberTotalNotBetween(Integer value1, Integer value2) {
            addCriterion("member_total not between", value1, value2, "memberTotal");
            return (Criteria) this;
        }

        public Criteria andDormNameIsNull() {
            addCriterion("dorm_name is null");
            return (Criteria) this;
        }

        public Criteria andDormNameIsNotNull() {
            addCriterion("dorm_name is not null");
            return (Criteria) this;
        }

        public Criteria andDormNameEqualTo(String value) {
            addCriterion("dorm_name =", value, "dormName");
            return (Criteria) this;
        }

        public Criteria andDormNameNotEqualTo(String value) {
            addCriterion("dorm_name <>", value, "dormName");
            return (Criteria) this;
        }

        public Criteria andDormNameGreaterThan(String value) {
            addCriterion("dorm_name >", value, "dormName");
            return (Criteria) this;
        }

        public Criteria andDormNameGreaterThanOrEqualTo(String value) {
            addCriterion("dorm_name >=", value, "dormName");
            return (Criteria) this;
        }

        public Criteria andDormNameLessThan(String value) {
            addCriterion("dorm_name <", value, "dormName");
            return (Criteria) this;
        }

        public Criteria andDormNameLessThanOrEqualTo(String value) {
            addCriterion("dorm_name <=", value, "dormName");
            return (Criteria) this;
        }

        public Criteria andDormNameLike(String value) {
            addCriterion("dorm_name like", value, "dormName");
            return (Criteria) this;
        }

        public Criteria andDormNameNotLike(String value) {
            addCriterion("dorm_name not like", value, "dormName");
            return (Criteria) this;
        }

        public Criteria andDormNameIn(List<String> values) {
            addCriterion("dorm_name in", values, "dormName");
            return (Criteria) this;
        }

        public Criteria andDormNameNotIn(List<String> values) {
            addCriterion("dorm_name not in", values, "dormName");
            return (Criteria) this;
        }

        public Criteria andDormNameBetween(String value1, String value2) {
            addCriterion("dorm_name between", value1, value2, "dormName");
            return (Criteria) this;
        }

        public Criteria andDormNameNotBetween(String value1, String value2) {
            addCriterion("dorm_name not between", value1, value2, "dormName");
            return (Criteria) this;
        }
    }

    public static class Criteria extends GeneratedCriteria {

        protected Criteria() {
            super();
        }
    }

    public static class Criterion {
        private String condition;

        private Object value;

        private Object secondValue;

        private boolean noValue;

        private boolean singleValue;

        private boolean betweenValue;

        private boolean listValue;

        private String typeHandler;

        public String getCondition() {
            return condition;
        }

        public Object getValue() {
            return value;
        }

        public Object getSecondValue() {
            return secondValue;
        }

        public boolean isNoValue() {
            return noValue;
        }

        public boolean isSingleValue() {
            return singleValue;
        }

        public boolean isBetweenValue() {
            return betweenValue;
        }

        public boolean isListValue() {
            return listValue;
        }

        public String getTypeHandler() {
            return typeHandler;
        }

        protected Criterion(String condition) {
            super();
            this.condition = condition;
            this.typeHandler = null;
            this.noValue = true;
        }

        protected Criterion(String condition, Object value, String typeHandler) {
            super();
            this.condition = condition;
            this.value = value;
            this.typeHandler = typeHandler;
            if (value instanceof List<?>) {
                this.listValue = true;
            } else {
                this.singleValue = true;
            }
        }

        protected Criterion(String condition, Object value) {
            this(condition, value, null);
        }

        protected Criterion(String condition, Object value, Object secondValue, String typeHandler) {
            super();
            this.condition = condition;
            this.value = value;
            this.secondValue = secondValue;
            this.typeHandler = typeHandler;
            this.betweenValue = true;
        }

        protected Criterion(String condition, Object value, Object secondValue) {
            this(condition, value, secondValue, null);
        }
    }
}