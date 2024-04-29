import styles from './Description.module.scss'
import React from 'react'
import description from '@/public/img/roadmap/description.png'
import Image from 'next/image'

const Description = () => {
  return (
    <>
      <div className={styles.context}>
        Data is the foundation to capturing the maximum value from AI technology and solving business problems quickly.
        To unlock the potential of generative AI technologies, however, there’s a key prerequisite: your data needs to
        be appropriately prepared. In this post, we describe how use generative AI to update and scale your data
        pipeline using Amazon SageMaker Canvas for data prep. Typically, data pipeline work requires a specialized skill
        to prepare and organize data for security analysts to use to extract value, which can take time, increase risks,
        and increase time to value. With SageMaker Canvas, security analysts can effortlessly and securely access
        leading foundation models to prepare their data faster and remediate cyber security risks. Data prep involves
        careful formatting and thoughtful contextualization, working backward from the customer problem. Now with the
        SageMaker Canvas chat for data prep capability, analysts with domain knowledge can quickly prepare, organize,
        and extract value from data using a chat-based experience.
      </div>
      <Image src={description} alt="" className={styles.image} />
      <div className={styles.context}>
        Data is the foundation to capturing the maximum value from AI technology and solving business problems quickly.
        To unlock the potential of generative AI technologies, however, there’s a key prerequisite: your data needs to
        be appropriately prepared. In this post, we describe how use generative AI to update and scale your data
        pipeline using Amazon SageMaker Canvas for data prep. Typically, data pipeline work requires a specialized skill
        to prepare and organize data for security analysts to use to extract value, which can take time, increase risks,
        and increase time to value. With SageMaker Canvas, security analysts can effortlessly and securely access
        leading foundation models to prepare their data faster and remediate cyber security risks. Data prep involves
        careful formatting and thoughtful contextualization, working backward from the customer problem. Now with the
        SageMaker Canvas chat for data prep capability, analysts with domain knowledge can quickly prepare, organize,
        and extract value from data using a chat-based experience.cyber security risks. Data prep involves careful
        formatting and thoughtful contextualization, working backward from the customer problem. Now with the SageMaker
        Canvas chat for data prep capability, analysts with domain knowledge can quickly prepare, organize, and extract
        value from data using a chat-based experience.
      </div>
    </>
  )
}

export default Description
