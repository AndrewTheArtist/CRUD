from flask_table import Table, Col, LinkCol


class CakeResults(Table):
    cake_id = Col('Id', show=False)
    cake_name = Col('Name')
    recipe_id = Col('Recipe ID')
    edit = LinkCol('Edit', 'edit_cake', url_kwargs=dict(id='cake_id'))
    delete = LinkCol('Delete', 'delete_cake', url_kwargs=dict(id='cake_id'))

class RecipeResults(Table):
    recipe_id = Col('Id')
    ingredients = Col('Ingredients')
    description = Col('Description')
    edit = LinkCol('Edit', 'edit_recipe', url_kwargs=dict(id='recipe_id'))
    delete = LinkCol('Delete', 'delete_recipe', url_kwargs=dict(id='recipe_id'))
