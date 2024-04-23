

resource "aws_db_subnet_group" "catalog" {
  name       = "catalog"
  subnet_ids = ["subnet-04b330e751a701a03", "subnet-071e3f2384e74aa83"]
  tags = {
    Name = "example"
  }
}

variable "db_username" {
  description = "RDS 데이터베이스의 마스터 사용자 이름"
  default     = "default"  # 기본값 설정
}

variable "db_master_password" {
  description = "RDS 데이터베이스의 마스터 암호"
  default     = "default"  # 기본값 설정
}

resource "aws_db_instance" "catalog" {
  identifier             = "catalog-db"
  instance_class         = "db.t3.micro"  # 프리티어 인스턴스 유형
  engine                 = "mysql"
  engine_version         = "8.0"
  allocated_storage      = 20             # 프리티어 스토리지 한도
  storage_type           = "gp2"
  db_subnet_group_name   = aws_db_subnet_group.catalog.name
  vpc_security_group_ids = ["sg-0c2237dd707dc6b09"]  # 보안 그룹 ID
  password = var.db_master_password
  username = var.db_username
  tags = {
    Name = "catalog-db"
  }
}