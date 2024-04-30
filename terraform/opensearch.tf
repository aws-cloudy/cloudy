resource "aws_opensearch_domain" "this" {
  domain_name    = "cloudy"
  engine_version = "OpenSearch_2.11"

  cluster_config {
    instance_type          = "t3.small.search"
    zone_awareness_enabled = false
    instance_count         = 1
  }
  ebs_options {
    ebs_enabled = true
    volume_size = 10
    volume_type = "gp2"
  }

  vpc_options {
    subnet_ids = [module.vpc.elasticache_subnets[0]]
    security_group_ids = [module.vpc.default_security_group_id]
  }

  advanced_security_options {
    enabled                        = true
    anonymous_auth_enabled         = false
    internal_user_database_enabled = true
    master_user_options {
      master_user_name     = "admin"
      master_user_password = "WkdS207Wkd!@#"
    }
  }
  node_to_node_encryption {
    enabled = true
  }
  encrypt_at_rest {
    enabled = true
  }
  domain_endpoint_options {
    enforce_https       = true
    tls_security_policy = "Policy-Min-TLS-1-2-2019-07"
  }
}

resource "aws_opensearch_domain_policy" "this" {
  domain_name     = aws_opensearch_domain.this.domain_name
  access_policies = data.aws_iam_policy_document.opensearch.json
}

data "aws_iam_policy_document" "opensearch" {
  statement {
    effect = "Allow"

    principals {
      type        = "*"
      identifiers = ["*"]
    }

    actions   = ["es:*"]
    resources = ["${aws_opensearch_domain.this.arn}/*"]
  }
}

resource "aws_iam_role_policy_attachment" "opensearch_vpc_access" {
  role       = aws_iam_role.ec2.name
  policy_arn = "arn:aws:iam::aws:policy/service-role/AmazonOpenSearchServiceRolePolicy"
}