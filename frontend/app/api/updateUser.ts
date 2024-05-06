import { CognitoIdentityServiceProvider } from 'aws-sdk';

const cognito = new CognitoIdentityServiceProvider({
  region: process.env.AWS_REGION, // AWS 리전 설정
});

export default async function handler(req: { method: string; body: { email: any; jobId: any; serviceId: any; }; }, res: { status: (arg0: number) => { (): any; new(): any; end: { (arg0: string): any; new(): any; }; json: { (arg0: { message?: string; error?: string; }): void; new(): any; }; }; }) {
  if (req.method !== 'POST') {
    return res.status(405).end('Method Not Allowed');
  }

  const { email, jobId, serviceId } = req.body;

  const params = {
    UserAttributes: [
      {
        Name: 'custom:job_id',
        Value: jobId
      },
      {
        Name: 'custom:service_id',
        Value: serviceId
      }
    ],
    UserPoolId: process.env.COGNITO_USER_POOL_ID as string, // Cognito 사용자 풀 ID
    Username: email // 사용자 이메일 or 사용자 이름
  };

  try {
    await cognito.adminUpdateUserAttributes(params).promise();
    res.status(200).json({ message: 'User attributes updated successfully' });
  } catch (error) {
    console.error('Error updating user attributes:', error);
    res.status(500).json({ error: 'Failed to update user attributes' });
  }
}
