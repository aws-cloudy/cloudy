import Select from 'react-select'
import styles from './Dropdown.module.scss'

interface DropdownProps {
  options: { value: string; label: string }[]
  value: { value: string; label: string } | null
  onChange: (option: any) => void
  placeholder: string
}

const customStyles = {
  control: (base: any) => ({
    ...base,
    height: 50,
    minheight: 50,
  }),
}

const theme = (theme: any) => ({
  ...theme,
  colors: {
    ...theme.colors,
    primary25: '#ff9900',
    primary75: 'black',
    primary: 'black',
  },
})

const Dropdown = ({ options, value, onChange, placeholder }: any) => {
  return (
    <Select
      options={options}
      value={value}
      onChange={onChange}
      className={styles.select}
      placeholder={placeholder}
      styles={customStyles}
      theme={theme}
    />
  )
}
export default Dropdown
