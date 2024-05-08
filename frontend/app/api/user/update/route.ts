import {
  AdminUpdateUserAttributesCommand,
  CognitoIdentityProviderClient,
} from '@aws-sdk/client-cognito-identity-provider'

import { NextResponse } from 'next/server'

const client = new CognitoIdentityProviderClient({
  region: process.env.AWS_REGION, // AWS 리전 설정
})

export async function POST(req: Request) {
  // console.log('Request', req)

  // req.body를 JSON으로 파싱
  const requestData = await req.json()
  const { username, jobId, serviceId } = requestData

  console.log(username, jobId, serviceId)
  const params = {
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
    UserPoolId: process.env.COGNITO_USER_POOL_ID as string, // Cognito 사용자 풀 ID
    Username: username,
  }
  try {
    await client.send(new AdminUpdateUserAttributesCommand(params))
    return NextResponse.json({ message: 'User attributes updated successfully' })
  } catch (error) {
    console.error('Error updating user attributes:', error)
    return NextResponse.json({ error: 'Failed to update user attributes' })
  }
}
