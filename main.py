import pymysql
import pymysql.cursors
from app import app
from tables import CakeResults, RecipeResults
from db_config import mysql
from flask import flash, render_template, request, redirect

# CAKE ROUTES


@app.route('/new_cake')
def add_cake_view():
    return render_template('add_cake.html')


@app.route('/add_cake', methods=['POST'])
def add_cake():
    conn = None
    cursor = None
    try:
        _name = request.form['inputName']
        _recipe = request.form['inputRecipe']
        # validate the received values
        if _name and _recipe and request.method == 'POST':
            # save edits
            sql = "INSERT INTO cake(cake_name, recipe_id) VALUES(%s, %s)"
            data = (_name, _recipe)
            conn = mysql.connect()
            cursor = conn.cursor()
            cursor.execute(sql, data)
            conn.commit()
            flash('Cake added successfully!')
            return redirect('/')
        else:
            return 'Error while adding cake'
    except Exception as e:
        print(e)
    finally:
        cursor.close()
        conn.close()


@app.route('/')
def view():
    conn = None
    cursor = None
    try:
        conn = mysql.connect()
        cursor = conn.cursor(pymysql.cursors.DictCursor)

        cursor.execute("SELECT * FROM cake")
        rows = cursor.fetchall()
        cake_table = CakeResults(rows)
        cake_table.border = True

        cursor.execute("SELECT * FROM recipe")
        rows = cursor.fetchall()
        recipe_table = RecipeResults(rows)
        recipe_table.border = True

        return render_template('view.html', cake_table=cake_table, recipe_table=recipe_table)
    except Exception as e:
        print(e)
    finally:
        cursor.close()
        conn.close()


@app.route('/edit_cake/<int:id>')
def edit_cake(id):
    conn = None
    cursor = None
    try:
        conn = mysql.connect()
        cursor = conn.cursor(pymysql.cursors.DictCursor)
        cursor.execute("SELECT * FROM cake WHERE cake_id=%s", id)
        row = cursor.fetchone()
        if row:
            return render_template('edit_cake.html', row=row)
        else:
            return 'Error loading #{id}'.format(id=id)
    except Exception as e:
        print(e)
    finally:
        cursor.close()
        conn.close()


@app.route('/update_cake', methods=['POST'])
def update_cake():
    conn = None
    cursor = None
    try:
        _name = request.form['inputName']
        _recipe = request.form['inputRecipe']
        _id = request.form['id']
        # validate the received values
        if _name and _recipe and _id and request.method == 'POST':
            # save edits
            sql = "UPDATE cake SET cake_name=%s, recipe_id=%s WHERE cake_id=%s"
            data = (_name, _recipe, _id,)
            conn = mysql.connect()
            cursor = conn.cursor()
            cursor.execute(sql, data)
            conn.commit()
            flash('Cake updated successfully!')
            return redirect('/')
        else:
            return 'Error while updating cake'
    except Exception as e:
        print(e)
    finally:
        cursor.close()
        conn.close()


@app.route('/delete_cake/<int:id>')
def delete_cake(id):
    conn = None
    cursor = None
    try:
        conn = mysql.connect()
        cursor = conn.cursor()
        cursor.execute("DELETE FROM cake WHERE cake_id=%s", (id,))
        conn.commit()
        flash('Cake deleted successfully!')
        return redirect('/')
    except Exception as e:
        print(e)
    finally:
        cursor.close()
        conn.close()


# RECIPE ROUTES


@app.route('/new_recipe')
def add_recipe_view():
    return render_template('add_recipe.html')


@app.route('/add_recipe', methods=['POST'])
def add_recipe():
    conn = None
    cursor = None
    try:
        _ingredients = request.form['inputIngredients']
        _desc = request.form['inputDescription']
        # validate the received values
        if _ingredients and _desc and request.method == 'POST':
            # save edits
            sql = "INSERT INTO recipe(ingredients, description) VALUES(%s, %s)"
            data = (_ingredients, _desc)
            conn = mysql.connect()
            cursor = conn.cursor()
            cursor.execute(sql, data)
            conn.commit()
            flash('Recipe added successfully!')
            return redirect('/')
        else:
            return 'Error while adding recipe'
    except Exception as e:
        print(e)
    finally:
        cursor.close()
        conn.close()


@app.route('/edit_recipe/<int:id>')
def edit_recipe(id):
    conn = None
    cursor = None
    try:
        conn = mysql.connect()
        cursor = conn.cursor(pymysql.cursors.DictCursor)
        cursor.execute("SELECT * FROM recipe WHERE recipe_id=%s", id)
        row = cursor.fetchone()
        if row:
            return render_template('edit_recipe.html', row=row)
        else:
            return 'Error loading #{id}'.format(id=id)
    except Exception as e:
        print(e)
    finally:
        cursor.close()
        conn.close()


@app.route('/update_recipe', methods=['POST'])
def update_recipe():
    conn = None
    cursor = None
    try:
        _ingredients = request.form['inputIngredients']
        _desc = request.form['inputDescription']
        _id = request.form['id']
        # validate the received values
        if _ingredients and _desc and _id and request.method == 'POST':
            # save edits
            sql = "UPDATE recipe SET ingredients=%s, description=%s WHERE recipe_id=%s"
            data = (_ingredients, _desc, _id,)
            conn = mysql.connect()
            cursor = conn.cursor()
            cursor.execute(sql, data)
            conn.commit()
            flash('Recipe updated successfully!')
            return redirect('/')
        else:
            return 'Error while updating recipe'
    except Exception as e:
        print(e)
    finally:
        cursor.close()
        conn.close()


@app.route('/delete_recipe/<int:id>')
def delete_recipe(id):
    conn = None
    cursor = None
    try:
        conn = mysql.connect()
        cursor = conn.cursor()
        cursor.execute("DELETE FROM recipe WHERE recipe_id=%s", (id,))
        conn.commit()
        flash('Recipe deleted successfully!')
        return redirect('/')
    except Exception as e:
        print(e)
    finally:
        cursor.close()
        conn.close()


if __name__ == "__main__":
    app.run()
