import { AdminGetUserCommand, CognitoIdentityProviderClient } from '@aws-sdk/client-cognito-identity-provider'

const client = new CognitoIdentityProviderClient({
  region: process.env.AWS_REGION,
  credentials: {
    accessKeyId: process.env.AWS_ACCESS_KEY_ID as string,
    secretAccessKey: process.env.AWS_SECRET_ACCESS_KEY as string,
  },
})

export async function checkUserExists(username: string): Promise<{ exists: boolean; hasJobId: boolean }> {
  const command = new AdminGetUserCommand({
    UserPoolId: process.env.COGNITO_USER_POOL_ID as string,
    Username: username,
  })

  try {
    const response = await client.send(command)
    const hasJobId = response.UserAttributes?.some(attr => attr.Name === 'custom:job_id' && attr.Value)
    return { exists: true, hasJobId: !!hasJobId }
  } catch (error: any) {
    if (error.name === 'UserNotFoundException') {
      return { exists: false, hasJobId: false }
    }
    throw error
  }
}
