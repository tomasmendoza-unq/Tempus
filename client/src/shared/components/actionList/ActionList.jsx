import { Header } from "../header/Header"

export const ActionList = ({
	items,
	headerTitle,
	headerAction,
	emptyMessage,
	renderItem,
}) => (
	<section>
		<Header title={headerTitle}>{headerAction}</Header>

		{items.length ? (
			items.map(renderItem)
		) : (
			<div className="py-10 text-center text-gray-400 italic text-sm">
				{emptyMessage}
			</div>
		)}
	</section>
)
