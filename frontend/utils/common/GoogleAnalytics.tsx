import ReactGA from 'react-ga4'

const gaId = process.env.NEXT_PUBLIC_GAID as string

const initializeGA = () => {
  ReactGA.initialize(gaId)
}

export default initializeGA
export { initializeGA }
