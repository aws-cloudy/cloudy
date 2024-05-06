import { AdminGetUserCommand, CognitoIdentityProviderClient } from '@aws-sdk/client-cognito-identity-provider';

const client = new CognitoIdentityProviderClient({
  region: process.env.AWS_REGION,
  credentials: {
    accessKeyId: process.env.AWS_ACCESS_KEY_ID as string,
    secretAccessKey: process.env.AWS_SECRET_ACCESS_KEY as string
  }
});


export async function checkUserExists(email: string): Promise<boolean> {
  const command = new AdminGetUserCommand({
    UserPoolId: process.env.COGNITO_USER_POOL_ID as string,
    Username: email
  });

  try {
    await client.send(command);
    return true;
  } catch (error:any) {
    if (error.name === 'UserNotFoundException') {
      return false;
    }
    throw error;
  }
}
