package tmp.generated_python;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class small_stmt3 extends small_stmt {
  public small_stmt3(del_stmt del_stmt, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyOne<del_stmt>("del_stmt", del_stmt)
    }, firstToken, lastToken);
  }
  public small_stmt3(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public IASTNode deepCopy() {
    return new small_stmt3(cloneProperties(),firstToken,lastToken);
  }
  public del_stmt getDel_stmt() {
    return ((PropertyOne<del_stmt>)getProperty("del_stmt")).getValue();
  }
}
