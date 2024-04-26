import Select from 'react-select'
import styles from './Dropdown.module.scss'

interface DropdownProps {
  options: { value: string; label: string }[]
  value: { value: string; label: string } | null
  onChange: (option: any) => void
  placeholder: string
  height: number //드롭다운 버튼의 높이
  width: number
}

const Dropdown = ({ options, value, onChange, placeholder, height = 50, width = 300 }: any) => {
  const customStyles = {
    control: (base: any) => ({
      ...base,
      height: height,
      minheight: height,
      width: width,
      minwidth: width,
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
