package tmp.generated_python;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class small_stmt6 extends small_stmt {
  public small_stmt6(import_stmt import_stmt, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyOne<import_stmt>("import_stmt", import_stmt)
    }, firstToken, lastToken);
  }
  public small_stmt6(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public IASTNode deepCopy() {
    return new small_stmt6(cloneProperties(),firstToken,lastToken);
  }
  public import_stmt getImport_stmt() {
    return ((PropertyOne<import_stmt>)getProperty("import_stmt")).getValue();
  }
}
