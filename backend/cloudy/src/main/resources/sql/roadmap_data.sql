
ALTER TABLE roadmap MODIFY COLUMN `desc` TEXT;

ALTER TABLE roadmap_comment MODIFY COLUMN `member_id` VARCHAR(255);
ALTER TABLE roadmap_comment
    CHANGE COLUMN `desc` content VARCHAR(255);

ALTER TABLE roadmap_comment
    ADD COLUMN reg_at TIMESTAMP;

INSERT INTO catalog.learning_roadmap (id, roadmap_id, learning_id, `rank`) VALUES (1, 1, 39, 1);
INSERT INTO catalog.learning_roadmap (id, roadmap_id, learning_id, `rank`) VALUES (2, 1, 249, 2);
INSERT INTO catalog.learning_roadmap (id, roadmap_id, learning_id, `rank`) VALUES (3, 1, 147, 3);
INSERT INTO catalog.learning_roadmap (id, roadmap_id, learning_id, `rank`) VALUES (4, 1, 57, 4);
INSERT INTO catalog.roadmap (id, title, `desc`, thumbnail, service, job, summary) VALUES (1, 'AWS 톺아보기', '<div>
<h2>클라우드 서비스 AWS</h2>
<h3>AWS1</h3>
클라우드 서비스인 AWS(Amazon Web Services)의 기본적인 사용법을 다루고 있는 수업입니다. 이 수업에서는 아래와 같은 내용을 배웁니다.
<blockquote>
<ul>
<li>어떤 서비스가 있는지를 파악하는 방법을 살펴보고, 윈도우 컴퓨터를 임대하기 위해서 필요한 서비스인 EC2를 살펴봅니다.</li>
<li>EC2를 구동시켜보면서 아마존 웹 서비스를 구동하는 일반적인 방법을 살펴보겠습니다.</li>
<li>EC2를 이용해서 장만한 윈도우 컴퓨터를 원격으로 제어하는 방법을 살펴보겠습니다.</li>
<li>서비스를 끄는 방법을 EC2의 사례로 살펴보겠습니다.</li>
<li>회원가입 방법 &amp; 요금을 확인하는 방법</li>
<li>아마존 웹 서비스는 아이디/비밀번호와 함께 일회용 비밀번호(OTP)를 사용하도록 권장하고 있습니다. 이렇게 두가지 인증방법을 사용하는 것을 Multi-factor Authentication(MFA)라고 합니다. 이것을 통해서 계정을 안전하게 지키는 방법을 살펴봅니다.</li>
</ul>
</blockquote>
<h3>AWS2 S3</h3>
클라우드 서비스인 Amazon Web Services에서 파일의 저장공간을 제공하는 S3를 다루고 있습니다.&nbsp;이 수업에서는 아래와 같은 내용을 배웁니다.
<blockquote>
<ul>
<li>S3의 구성요소인 버킷(bucket), 폴더(folder), 객체(Object)를 살펴봅니다.</li>
<li>독립된 저장공간인 버킷(Bucket)의 사용법을 살펴보겠습니다.</li>
<li>S3에서 폴더를 만드는 법을 살펴보겠습니다.</li>
<li>S3의 객체는 파일을 의미합니다. 파일을 업로드해서 객체를 만드는 방법을 살펴보겠습니다.</li>
<li>S3의 객체를 공유하기 위해서는 권한을 조정해야 합니다. 기본적인 권한의 사용법을&nbsp;살펴보겠습니다.</li>
<li>S3는 여러가지 종류의 저장공간을 서비스로 제공하고 있습니다. 각각의 장단점을 비교해겠습니다.</li>
<li>S3의 요금 체계를 살펴보겠습니다.</li>
</ul>
</blockquote>
</div>
<h3>AWS2 CloudFront</h3>
<p>전세계에 있는 사용자들이 비슷한 속도로 웹서비스를 받아볼 수 있도록 하는 기술이&nbsp;CDN입니다. 이 수업에서는 아래와 같은 내용을 배웁니다.</p>
<blockquote>
<ul>
<li>우리는 어떤 문제를 가지고 있고, CloudFront를 통해서 이 문제를 어떻게 극복할 것인가를 소개합니다. 핵심은 성능과 정보의 신선도 입니다.</li>
<li>클라우드 프론트를 생성하는 방법을 살펴봅니다.</li>
<li>CloudFront의 핵심은 캐쉬 서버입니다. 캐쉬를 설정하는 방법을 자세히 알아봅니다.</li>
<li>AWS는 전세계에 캐쉬 서버를 유지하고 있습니다. CloudFront를 이용해 빠른 속도로 전세계의 사용자들에게 정보를 제공하는 방법을 살펴봅니다.</li>
<li>CloudFront의 요금 정책을 살펴보겠습니다.</li>
</ul>
</blockquote>
<h3>AWS2 RDS</h3>
<p>관계형 데이터베이스를 서비스해주는 AWS의 RDS(Relational Database Service)를 다루고 있는 수업입니다. 데이터베이스 서버를 생성하고, 상태를 파악하고, 사용하고, 수정하고, 삭제하는 방법을 다룹니다.&nbsp;이 수업에서는 아래와 같은 내용을 배웁니다.</p>
<blockquote>
<ul>
<li>RDS에서 데이터베이스 서버를 생성하는 방법을 알아봅니다. 이 수업에서는 MySQL을 기준으로 설명합니다만, 다른 데이터베이스 제품도 기본적인 사용법은 비슷합니다.</li>
<li>데이터베이스 서버에 접속하는 방법을 살펴보겠습니다.</li>
<li>RDS에서 데이터베이스 서버를 모니터링하는 방법을 살펴봅니다.</li>
<li>RDS 데이터베이스 서버의 데이터를 백업하고, 복원하는 방법을 알아보겠습니다.</li>
<li>RDS의 요금체계를 살펴보겠습니다.</li>
</ul>
</blockquote>
<div>
<h3><img class="alignnone wp-image-233795" src="https://cdn.inflearn.com/wp-content/uploads/aws-logo.png" alt="" width="55" height="45" />학습 목표</h3>
<ul>
<li style="list-style-type: none;">
<ul>
<li>AWS(Amazon Web Services)의 기본적인 사용법을 다룰 수 있다.</li>
<li>AWS(Amazon Web Services)의 S3, CloudFront, RDS를 다룰 수 있다.</li>
</ul>
</li>
</ul>
<h3><img class="alignnone wp-image-233795" src="https://cdn.inflearn.com/wp-content/uploads/aws-logo.png" alt="" width="55" height="45" />추천 학습 순서</h3>
<h3><img class="alignnone size-medium wp-image-247617" src="https://cdn.inflearn.com/wp-content/uploads/캡처-3-460x457.png" alt="AWS " width="300" height="298" /></h3>
</div>
<div style="margin-bottom: 20px;">
<h3><img class="alignnone wp-image-233795" src="https://cdn.inflearn.com/wp-content/uploads/aws-logo.png" alt="" width="55" height="45" />도움이 되는 분들</h3>
<h3>[AWS1]</h3>
<ul>
<li>회원가입과 탈퇴, 서비스의 실행과 종료, 자금 관리와 보안과 같은 부분이 필요한 분들을 위한 수업입니다.</li>
</ul>
<h3>[AWS2 S3]</h3>
<ul>
<li>S3의 본질적인 기능이 궁금하신 분들을 위한 수업입니다.</li>
</ul>
<h3>[AWS2 CloudFront]</h3>
<ul>
<li>AWS에서 제공하는 CDN 서비스인 CloudFront의 기능이 궁금하신 분들을 위한 수업입니다.</li>
</ul>
<h3>[AWS2 RDS]</h3>
<ul>
<li>관계형 데이터베이스를 서비스해주는 AWS의 RDS(Relational Database Service)를 통해 데이터베이스 서버를 운영하는 방법이 궁금하신 분들을 위한 수업입니다.</li>
</ul>
</div>', 'https://cdn.inflearn.com/wp-content/uploads/AWS-1.png', 'Developing', 'Developer', '클라우드 서비스인 AWS(Amazon Web Services)의 기본적인 사용법을 다루고 있는 수업입니다');
