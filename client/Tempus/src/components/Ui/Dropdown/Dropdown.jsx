import { useState } from "react"
import useOutsideClick from "../../../hooks/useOnBlur"
import { ArrowDown, ArrowUp } from "feather-icons-react"

export default function DropDown({
  elements,
  tag1,
  tag2,
  callback,
  selected = <ArrowUp />,
  unSelected = <ArrowDown />,
}) {
  const [isOpen, setIsOpen] = useState(false)
  const ref = useOutsideClick(() => setIsOpen(false))

  let setOfElems = [
    ...new Set(
      elements?.map((elem) => {
        return tag1 ? [elem?.[tag1], elem?.[tag2]] : elem
      })
    ),
  ]

  return (
    <div ref={ref} className="z-50 relative">
      <button
        onClick={(e) => {
          e.stopPropagation()
          setIsOpen((prevIsOpen) => !prevIsOpen)
        }}
      >
        {<>{isOpen ? selected : unSelected}</>}
      </button>
      {isOpen && (
        <div className="absolute right-0 mt-1 w-full">
          <ul className="flex bg-red-950 shadow-md rounded-md mt-1 absolute flex-col overflow-y-auto max-h-60 text-white">
            {setOfElems.map((elem, index) => {
              return (
                <li
                  key={index}
                  className="px-4 py-2 hover:bg-red-800 hover:rounded-md cursor-pointer w-full truncate"
                  onClick={() => {
                    callback(elem)
                    setIsOpen(false)
                  }}
                >
                  {elem?.[0] || elem}
                </li>
              )
            })}
          </ul>
        </div>
      )}
    </div>
  )
}
