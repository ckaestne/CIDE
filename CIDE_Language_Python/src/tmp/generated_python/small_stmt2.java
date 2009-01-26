package tmp.generated_python;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class small_stmt2 extends small_stmt {
  public small_stmt2(print_stmt print_stmt, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyOne<print_stmt>("print_stmt", print_stmt)
    }, firstToken, lastToken);
  }
  public small_stmt2(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public IASTNode deepCopy() {
    return new small_stmt2(cloneProperties(),firstToken,lastToken);
  }
  public print_stmt getPrint_stmt() {
    return ((PropertyOne<print_stmt>)getProperty("print_stmt")).getValue();
  }
}
