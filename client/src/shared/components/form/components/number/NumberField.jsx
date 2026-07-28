export default function NumberField({
	name,
	label,
	value,
	onChange,
	placeholder,
	min,
	max,
}) {
	return (
		<div>
			{label && (
				<label className="block text-sm font-medium text-gray-700">
					{label}
				</label>
			)}
			<input
				type="number"
				name={name}
				value={value}
				onChange={onChange}
				placeholder={placeholder}
				min={min}
				max={max}
				className="mt-1 block w-24 border-gray-300 rounded-md shadow-sm border p-2"
			/>
		</div>
	)
}
