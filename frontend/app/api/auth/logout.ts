async function handleLogout(
  req: any,
  res: { status?: (arg0: any) => { (): any; new (): any; end: { (arg0: any): void; new (): any } }; redirect?: any },
) {
  const result = res.redirect(
    `${process.env.COGNITO_DOMAIN}/logout/client_id=${process.env.COGNITO_CLIENT_ID}&logout_uri=${process.env.COGNITO_LOGOUT_URL}`,
  )
  console.log('로그아웃합니다.')
}

export default async function logout(
  req: any,
  res: { status: (arg0: any) => { (): any; new (): any; end: { (arg0: any): void; new (): any } } },
) {
  try {
    await handleLogout(req, res)
  } catch (error: any) {
    console.error(error)
    res.status(error.status || 400).end(error.message)
  }
}
