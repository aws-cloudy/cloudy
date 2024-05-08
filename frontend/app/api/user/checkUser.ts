import { CognitoIdentityProviderClient, AdminGetUserCommand } from '@aws-sdk/client-cognito-identity-provider'

export default async function handler(
  req: { method: string; body: { username: any } },
  res: {
    status: (arg0: number) => {
      (): any
      new (): any
      end: { (arg0: string): any; new (): any }
      json: { (arg0: { message?: string; error?: string }): void; new (): any }
    }
  },
) {
  if (req.method !== 'POST') {
    return res.status(405).end('Method Not Allowed')
  }

  const { username } = req.body
  const client = new CognitoIdentityProviderClient({ region: 'us-west-2' })

  const command = new AdminGetUserCommand({
    UserPoolId: process.env.AMPLIFY_USERPOOL_ID,
    Username: username,
  })

  try {
    await client.send(command)
    res.status(200).json({ message: 'User exists' })
  } catch (error: any) {
    if (error.name === 'UserNotFoundException') {
      res.status(404).json({ message: 'User not found' })
    } else {
      res.status(500).json({ error: 'Internal Server Error' })
    }
  }
}
