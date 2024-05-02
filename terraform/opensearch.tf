

resource "aws_opensearch_domain" "cloudy" {
  domain_name           = "cloudy"
  engine_version        = "OpenSearch_2.11"

  cluster_config {
    instance_type     = "t3.small.search" # 프리티어 가능 인스턴스
    instance_count    = 1
  }

  ebs_options {
    ebs_enabled       = true
    volume_size       = 10
    volume_type       = "gp2"
  }

  domain_endpoint_options {
    enforce_https = true
    tls_security_policy = "Policy-Min-TLS-1-2-2019-07"
  }

  node_to_node_encryption {
    enabled = true
  }

  encrypt_at_rest {
    enabled = true
  }

  advanced_security_options {
    enabled = true
    internal_user_database_enabled = true
    master_user_options {
      master_user_name = "${user_name}"
      master_user_password = "${password}"
    }
  }

  access_policies = <<POLICY
{
  "Version": "2012-10-17",
  "Statement": [
    {
      "Effect": "Allow",
      "Principal": "*",
      "Action": "es:*",
      "Resource": "arn:aws:es:ap-northeast-2:767397922066:domain/cloudy/*"
    }
  ]
}
POLICY

  snapshot_options {
    automated_snapshot_start_hour = 23
  }

}

output "opensearch_domain_endpoint" {
  value = aws_opensearch_domain.cloudy.endpoint
}

output "opensearch_domain_arn" {
  value = aws_opensearch_domain.cloudy.arn
}