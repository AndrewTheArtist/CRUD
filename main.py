import pymysql
import pymysql.cursors
from app import app
from tables import CakeResults, ChefResults
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
            sql = "INSERT INTO cake(cake_name, recipe) VALUES(%s, %s)"
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

        cursor.execute("SELECT * FROM chef")
        rows = cursor.fetchall()
        chef_table = ChefResults(rows)
        chef_table.border = True

        return render_template('view.html', cake_table=cake_table, chef_table=chef_table)
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
            sql = "UPDATE cake SET cake_name=%s, recipe=%s WHERE cake_id=%s"
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


# CHEF ROUTES


@app.route('/new_chef')
def add_chef_view():
    return render_template('add_chef.html')


@app.route('/add_chef', methods=['POST'])
def add_chef():
    conn = None
    cursor = None
    try:
        _name = request.form['inputName']
        _contact = request.form['inputContact']
        _cakes = request.form['inputCakes']
        # validate the received values
        if _contact and _cakes and request.method == 'POST':
            # save edits
            sql = "INSERT INTO chef(chef_name, contact, best_cakes) VALUES(%s, %s, %s)"
            data = (_name, _contact, _cakes)
            conn = mysql.connect()
            cursor = conn.cursor()
            cursor.execute(sql, data)
            conn.commit()
            flash('Chef added successfully!')
            return redirect('/')
        else:
            return 'Error while adding chef'
    except Exception as e:
        print(e)
    finally:
        cursor.close()
        conn.close()


@app.route('/edit_chef/<int:id>')
def edit_chef(id):
    conn = None
    cursor = None
    try:
        conn = mysql.connect()
        cursor = conn.cursor(pymysql.cursors.DictCursor)
        cursor.execute("SELECT * FROM chef WHERE chef_id=%s", id)
        row = cursor.fetchone()
        if row:
            return render_template('edit_chef.html', row=row)
        else:
            return 'Error loading #{id}'.format(id=id)
    except Exception as e:
        print(e)
    finally:
        cursor.close()
        conn.close()


@app.route('/update_chef', methods=['POST'])
def update_chef():
    conn = None
    cursor = None
    try:
        _name = request.form['inputName']
        _contact = request.form['inputContact']
        _cakes = request.form['inputCakes']
        _id = request.form['id']
        # validate the received values
        if _name and _contact and _cakes and _id and request.method == 'POST':
            # save edits
            sql = "UPDATE chef SET chef_name=%s, contact=%s, best_cakes=%s WHERE chef_id=%s"
            data = (_name, _contact, _cakes, _id,)
            conn = mysql.connect()
            cursor = conn.cursor()
            cursor.execute(sql, data)
            conn.commit()
            flash('Chef updated successfully!')
            return redirect('/')
        else:
            return 'Error while updating chef'
    except Exception as e:
        print(e)
    finally:
        cursor.close()
        conn.close()


@app.route('/delete_chef/<int:id>')
def delete_chef(id):
    conn = None
    cursor = None
    try:
        conn = mysql.connect()
        cursor = conn.cursor()
        cursor.execute("DELETE FROM chef WHERE chef_id=%s", (id,))
        conn.commit()
        flash('Chef deleted successfully!')
        return redirect('/')
    except Exception as e:
        print(e)
    finally:
        cursor.close()
        conn.close()


if __name__ == "__main__":
    app.run()
