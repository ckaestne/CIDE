package tmp.generated_c;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class StorageClassSpecifier4 extends StorageClassSpecifier {
  public StorageClassSpecifier4(ASTStringNode extern, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyOne<ASTStringNode>("extern", extern)
    }, firstToken, lastToken);
  }
  public StorageClassSpecifier4(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public IASTNode deepCopy() {
    return new StorageClassSpecifier4(cloneProperties(),firstToken,lastToken);
  }
  public ASTStringNode getExtern() {
    return ((PropertyOne<ASTStringNode>)getProperty("extern")).getValue();
  }
}
