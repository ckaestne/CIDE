package tmp.generated_python;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class AnyName24 extends AnyName {
  public AnyName24(ASTStringNode import_kw, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyOne<ASTStringNode>("import_kw", import_kw)
    }, firstToken, lastToken);
  }
  public AnyName24(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public IASTNode deepCopy() {
    return new AnyName24(cloneProperties(),firstToken,lastToken);
  }
  public ASTStringNode getImport_kw() {
    return ((PropertyOne<ASTStringNode>)getProperty("import_kw")).getValue();
  }
}
