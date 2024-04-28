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

    public static LearningItem getDummyLearningItem3() {
        return LearningItem.builder()
                .learningId(4)
                .thumbnail("//explore.skillbuilder.aws/files/a/w/aws_prod1_docebosaas_com/assets/courselogo/original/course-type-digital-2021-09-02-21-37-20.jpeg?enhanced_signature=WAWBQ6-SZZMRoD29b39-toUWB29a2MsqCb_i9L05qKs")
                .serviceType("Data Analytics")
                .title("Getting Started with Amazon EMR (Korean)")
                .duration("1h")
                .difficulty("Fundamental")
                .link("https://explore.skillbuilder.aws/learn/course/external/view/elearning/12839/getting-started-with-amazon-emr-korean")
                .build();
    }

    public static LearningItem getDummyLearningItem4() {
        return LearningItem.builder()
                .learningId(6)
                .thumbnail("//explore.skillbuilder.aws/files/a/w/aws_prod1_docebosaas_com/assets/courselogo/original/course-type-digital-2021-09-02-21-37-20.jpeg?enhanced_signature=WAWBQ6-SZZMRoD29b39-toUWB29a2MsqCb_i9L05qKs")
                .serviceType("Data Analytics")
                .title("Getting Started with Amazon Redshift (Korean)")
                .duration("1h")
                .difficulty("Fundamental")
                .link("https://explore.skillbuilder.aws/learn/course/external/view/elearning/16153/getting-started-with-amazon-redshift-korean")
                .build();
    }

    public static Learning getDummyLearning1() {
        return Learning.builder()
                .title("Introduction to Amazon Athena (Korean)")
                .difficulty("Fundamental")
                .duration("10m")
                .desc("<p>이 과정에서는 Amazon Athena 서비스를 소개하고 그 운영 환경의 개요를 설명합니다. Amazon Athena를 구현하는 기본 단계도 다룹니다. 검증을 위해 SQL 쿼리를 실행할 데이터베이스를 AWS Management Console을 사용하여 생성하는 간단한 데모를 수행합니다.</p>")
                .thumbnail("//explore.skillbuilder.aws/files/a/w/aws_prod1_docebosaas_com/assets/courselogo/original/course-type-digital-2021-09-02-21-37-20.jpeg?enhanced_signature=yC5WA1SQjxbpnm4fJm8ztKE6n1P-Zw215RU6-PjydKU")
                .link("https://explore.skillbuilder.aws/learn/course/external/view/elearning/5576/introduction-to-amazon-athena-korean")
                .type("Digital Course")
                .build();
    }

    public static Learning getDummyLearning2() {
        return Learning.builder()
                .title("Visualizing with QuickSight (Korean)")
                .difficulty("Fundamental")
                .duration("4h")
                .desc("<p>이 과정에서는 Amazon Web Services(AWS)를 사용한 비즈니스 인텔리전스(BI) 및 데이터 시각화의 기술적 측면을 소개합니다. Amazon QuickSight를 사용하여 대화형 대시보드 및 분석을 구축하고 공유하는 것을 중심으로, 대시보드를 애플리케이션 및 웹 사이트에 임베딩하고 액세스 및 권한을 안전하게 관리하는 방법을 배우게 됩니다. 또한 Amazon Simple Storage Service(Amazon S3), Amazon Relational Database Service(Amazon RDS), Amazon Redshift, Amazon Athena 및 AWS Glue와 같은 서비스의 데이터와 Virtual Private Cloud(VPC) 및 애플리케이션을 통해 온프레미스 데이터 소스에 연결하고 준비하는 방법도 알아봅니다.</p><p><br></p><p>참고: 이 과정의 동영상에는 한국어 트랜스크립트 또는 자막이 지원되며 음성은 영어로 출력됩니다. 자막을 표시하려면 동영상 화면 하단의 CC 버튼을 클릭하세요.</p><p><br></p><h2>수강 대상</h2><p>이 과정의 대상은 다음과 같습니다.</p><ul><li>AWS 고객 및 파트너 조직의 기술 전문가, BI 엔지니어 및 데이터 분석가</li></ul><p><br></p><h2>과정 목표</h2><p>이 과정에서 학습할 내용은 다음과 같습니다.</p><ul><li>QuickSight를 통한 데이터 시각화에 대한 기술 개요 제공 &nbsp; &nbsp; </li><li>AWS 소스, 서드 파티 애플리케이션 및 온프레미스 데이터베이스에서 시각화 및 분석을 위한 데이터 연결 및 준비 &nbsp;</li><li>시각적 분석 및 대시보드 생성 및 공유 </li><li>&nbsp;Active Directory, Security Assertion Markup Language(SAML) 및 소프트웨어 개발 키트(SDK) 등 사용자 및 그룹에 대한 보안 인증 설정 &nbsp;</li><li>애플리케이션, 웹 사이트 및 포털에 대화형 대시보드 및 분석 임베딩 &nbsp; </li><li>이상 탐지, 예측 및 자연어 서술에 자동화된 기계 학습 인사이트 활용</li></ul><p><br></p><h2>사전 조건</h2><p>이 과정을 수강하기 전에 다음 사전 조건을 갖추는 것이 좋습니다.</p><ul><li>AWS Technical Professional </li><li>BI 보고 도구에 대한 사전 경험</li></ul><p><br></p><h2>강의 형태</h2><p>이 과정은 다음 방법을 통해 제공됩니다.</p><ul><li> 자습형 디지털 교육</li></ul><p><br></p><h2>소요 시간</h2><p>4시간</p><p><br></p><h2>과정 개요</h2><p>이 과정에서는 다음 개념을 다룹니다.</p><ul><li>QuickSight의 기본 소개 및 이해 &nbsp;</li><li>QuickSight에서 데이터 시각화 구축 및 공유 </li><li>ML insights 작업 </li><li>QuickSight 대시보드 임베딩 &nbsp;</li><li>QuickSight 구현</li></ul>")
                .thumbnail("//explore.skillbuilder.aws/files/a/w/aws_prod1_docebosaas_com/assets/courselogo/original/course-type-digital-2021-09-02-21-37-20.jpeg?enhanced_signature=yC5WA1SQjxbpnm4fJm8ztKE6n1P-Zw215RU6-PjydKU")
                .link("https://explore.skillbuilder.aws/learn/course/external/view/elearning/6232/visualizing-with-quicksight-korean")
                .type("Digital Course")
                .build();
    }


//    public static LearningItem changeLearningToLearningItem(Learning learning) {
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

    public static LearningListRes getDummyLearningListRes(List<LearningItem> list) {
        return LearningListRes.builder()
                .learningList(list)
                .build();
    }
}
