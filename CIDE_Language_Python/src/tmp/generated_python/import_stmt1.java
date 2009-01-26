package tmp.generated_python;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class import_stmt1 extends import_stmt {
  public import_stmt1(ASTStringNode import_kw, Import import_KW, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyOne<ASTStringNode>("import_kw", import_kw),
      new PropertyOne<Import>("import_KW", import_KW)
    }, firstToken, lastToken);
  }
  public import_stmt1(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public IASTNode deepCopy() {
    return new import_stmt1(cloneProperties(),firstToken,lastToken);
  }
  public ASTStringNode getImport_kw() {
    return ((PropertyOne<ASTStringNode>)getProperty("import_kw")).getValue();
  }
  public Import getImport_KW() {
    return ((PropertyOne<Import>)getProperty("import_KW")).getValue();
  }
}
