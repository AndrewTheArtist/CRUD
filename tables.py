from flask_table import Table, Col, LinkCol


class CakeResults(Table):
    cake_id = Col('Id', show=False)
    cake_name = Col('Name')
    recipe = Col('Recipe')
    edit = LinkCol('Edit', 'edit_cake', url_kwargs=dict(id='cake_id'))
    delete = LinkCol('Delete', 'delete_cake', url_kwargs=dict(id='cake_id'))


class ChefResults(Table):
    chef_id = Col('Id')
    chef_name = Col('Name')
    contact = Col('Contact')
    best_cakes = Col('Best Cakes')
    edit = LinkCol('Edit', 'edit_chef', url_kwargs=dict(id='chef_id'))
    delete = LinkCol('Delete', 'delete_chef', url_kwargs=dict(id='chef_id'))
