import { IFilter } from '@/types/learning'
import CustomSelect from '.'
import { fireEvent, render } from '@testing-library/react'

describe('CustomSelect', () => {
  const options: IFilter[] = [
    { category: 'job', name: 'option1', value: 'option1' },
    { category: 'job', name: 'option2', value: 'option2' },
  ]
  it('클릭했을 때 options가 열립니다', () => {
    const item: IFilter = { category: 'job', name: 'option1', value: 'option1' }

    const { getAllByText, getByRole } = render(<CustomSelect item={item} setItem={jest.fn()} options={options} />)
    fireEvent.click(getAllByText('option1')[0])
    expect(getByRole('listbox')).toBeInTheDocument()
  })

  it('option을 클릭했을 때 setItem 함수가 실행됩니다', () => {
    const item: IFilter = { category: 'job', name: 'option1', value: 'option1' }
    const setItem = jest.fn()

    const { getAllByText } = render(<CustomSelect item={item} setItem={setItem} options={options} />)
    fireEvent.click(getAllByText('option2')[0])
    expect(setItem).toHaveBeenCalledWith({ category: 'job', name: 'option2', value: 'option2' })
  })
})
