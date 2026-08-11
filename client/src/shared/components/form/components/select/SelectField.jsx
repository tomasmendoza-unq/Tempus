export default function SelectField({ name, label, value, onChange, options }) {
	return (
		<select
			name={name}
			value={value}
			onChange={onChange}
			className="w-full border border-gray-300 rounded-md p-2"
		>
			<option value="">{label}</option>
			{options.map((option) => (
				<option key={option.value} value={option.value}>
					{option.label}
				</option>
			))}
		</select>
	)
}
