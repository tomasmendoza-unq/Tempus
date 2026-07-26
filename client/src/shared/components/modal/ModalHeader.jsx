export const ModalHeader = ({ title, subtitle }) => (
	<div className="mb-6 border-b pb-4">
		<h2 className="text-2xl font-extrabold text-red-950 italic">{title}</h2>
		<p className="text-gray-500 text-xs uppercase tracking-widest font-bold">
			{subtitle}
		</p>
	</div>
)
