package tmp.generated_c;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class StorageClassSpecifier3 extends StorageClassSpecifier {
  public StorageClassSpecifier3(ASTStringNode static_kw, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyOne<ASTStringNode>("static_kw", static_kw)
    }, firstToken, lastToken);
  }
  public StorageClassSpecifier3(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public IASTNode deepCopy() {
    return new StorageClassSpecifier3(cloneProperties(),firstToken,lastToken);
  }
  public ASTStringNode getStatic_kw() {
    return ((PropertyOne<ASTStringNode>)getProperty("static_kw")).getValue();
  }
}
