import {
  CognitoIdentityProviderClient,
  ListUsersCommand,
  AdminUpdateUserAttributesCommand,
} from '@aws-sdk/client-cognito-identity-provider'

const client = new CognitoIdentityProviderClient({
  region: process.env.AWS_REGION as string,
  credentials: {
    accessKeyId: process.env.AWS_ACCESS_KEY_ID as string,
    secretAccessKey: process.env.AWS_SECRET_ACCESS_KEY as string,
  },
})

// 사용자 존재 여부를 확인
async function checkUserExists(email: any) {
  const params = {
    UserPoolId: process.env.COGNITO_USER_POOL_ID,
    Filter: `email = "${email}"`, // 이메일을 기반으로 사용자를 찾기 위한 필터
    Limit: 1,
  }

  const command = new ListUsersCommand(params)

  try {
    const { Users } = await client.send(command)
    return Users && Users.length > 0 // 사용자가 존재하면 true, 그렇지 않으면 false를 반환
  } catch (error) {
    console.error('Error checking user existence:', error)
    throw error
  }
}

// 사용자 추가 정보를 Cognito 사용자 풀에 저장
async function saveUserDetails(userId: any, jobId: any, serviceId: any) {
  const params = {
    UserPoolId: process.env.COGNITO_USER_POOL_ID,
    Username: userId,
    UserAttributes: [
      {
        Name: 'custom:job_id',
        Value: jobId,
      },
      {
        Name: 'custom:service_id',
        Value: serviceId,
      },
    ],
  }
  const command = new AdminUpdateUserAttributesCommand(params)

  try {
    const response = await client.send(command)
    return response
  } catch (error) {
    console.error('Failed to update user details in Cognito:', error)
    throw error
  }
}

export { checkUserExists, saveUserDetails }
