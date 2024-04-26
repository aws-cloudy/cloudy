package com.s207.cloudy.dummy.learning;

import com.s207.cloudy.domain.learning.dto.LearningItem;
import com.s207.cloudy.domain.learning.dto.LearningListRes;
import com.s207.cloudy.domain.learning.entity.Learning;

import java.util.List;

public class DummyLearning {

    public static LearningItem getDummyLearningItem1() {
        return LearningItem.builder()
                .learningId(1)
                .thumbnail("//explore.skillbuilder.aws/files/a/w/aws_prod1_docebosaas_com/assets/courselogo/original/course-type-digital-2021-09-02-21-37-20.jpeg?enhanced_signature=yC5WA1SQjxbpnm4fJm8ztKE6n1P-Zw215RU6-PjydKU")
                .serviceType("Data Analytics")
                .title("Introduction to Amazon Athena (Korean)")
                .duration("10m")
                .difficulty("Fundamental")
                .link("https://explore.skillbuilder.aws/learn/course/external/view/elearning/5576/introduction-to-amazon-athena-korean")
                .build();
    }

    public static LearningItem getDummyLearningItem2() {
        return LearningItem.builder()
                .learningId(2)
                .thumbnail("//explore.skillbuilder.aws/files/a/w/aws_prod1_docebosaas_com/assets/courselogo/original/course-type-digital-2021-09-02-21-37-20.jpeg?enhanced_signature=yC5WA1SQjxbpnm4fJm8ztKE6n1P-Zw215RU6-PjydKU")
                .serviceType("Cloud Essentials")
                .title("Visualizing with QuickSight (Korean)")
                .duration("4h")
                .difficulty("Fundamental")
                .link("https://explore.skillbuilder.aws/learn/course/external/view/elearning/6232/visualizing-with-quicksight-korean")
                .build();
    }

//    public static Learning getDummyLearning() {
//        return Learning.builder()
//                .thumbnail("//explore.skillbuilder.aws/files/a/w/aws_prod1_docebosaas_com/assets/courselogo/original/course-type-digital-2021-09-02-21-37-20.jpeg?enhanced_signature=yC5WA1SQjxbpnm4fJm8ztKE6n1P-Zw215RU6-PjydKU")
//                .type("Data Analytics")
//                .title("Introduction to Amazon Athena (Korean)")
//                .duration("10m")
//                .difficulty("Fundamental")
//                .link("https://explore.skillbuilder.aws/learn/course/external/view/elearning/5576/introduction-to-amazon-athena-korean")
//                .build();
//    }

//    public static LearningItem getDummyLearningItem(Learning learning) {
//        return LearningItem.of(learning);
//    }

    public static LearningListRes getDummyLearningListRes(List<LearningItem> list, Boolean isModified, String modifiedKeyword) {
        return LearningListRes.builder()
                .learningList(list)
                .isModified(isModified)
                .modifiedKeyword(modifiedKeyword)
                .build();
    }

    public static LearningListRes getDummyLearningListRes(List<LearningItem> list, Boolean isModified) {
        return LearningListRes.builder()
                .learningList(list)
                .isModified(isModified)
                .build();
    }
}
