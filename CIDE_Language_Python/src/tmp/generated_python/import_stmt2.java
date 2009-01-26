package tmp.generated_python;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class import_stmt2 extends import_stmt {
  public import_stmt2(ASTStringNode from, ImportFrom importFrom, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyOne<ASTStringNode>("from", from),
      new PropertyOne<ImportFrom>("importFrom", importFrom)
    }, firstToken, lastToken);
  }
  public import_stmt2(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public IASTNode deepCopy() {
    return new import_stmt2(cloneProperties(),firstToken,lastToken);
  }
  public ASTStringNode getFrom() {
    return ((PropertyOne<ASTStringNode>)getProperty("from")).getValue();
  }
  public ImportFrom getImportFrom() {
    return ((PropertyOne<ImportFrom>)getProperty("importFrom")).getValue();
  }
}
