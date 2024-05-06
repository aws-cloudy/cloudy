import { AdminUpdateUserAttributesCommand, CognitoIdentityProviderClient } from '@aws-sdk/client-cognito-identity-provider';
import { NextResponse } from 'next/server';


const client = new CognitoIdentityProviderClient({
  region: process.env.AWS_REGION // AWS 리전 설정
});

export async function POST(req:Request) {

  const {jobId, serviceId } = req.body;

  const params = {
    UserAttributes: [
      {
        Name: 'custom:job_id',
        Value: "0"
      },
      {
        Name: 'custom:service_id',
        Value: "1"
      }
    ],
    UserPoolId: process.env.COGNITO_USER_POOL_ID as string, // Cognito 사용자 풀 ID
    Username: 'google_101390828415766152009' // 사용자 이메일 or 사용자 이름
  };

  try {
    await client.send(new AdminUpdateUserAttributesCommand(params));
    return NextResponse.json({ message: 'User attributes updated successfully' });
  } catch (error) {
    console.error('Error updating user attributes:', error);
    return NextResponse.json({ error: 'Failed to update user attributes' });
  }
}
