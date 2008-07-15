package tmp.generated_c;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class StorageClassSpecifier5 extends StorageClassSpecifier {
  public StorageClassSpecifier5(ASTStringNode typedef, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyOne<ASTStringNode>("typedef", typedef)
    }, firstToken, lastToken);
  }
  public StorageClassSpecifier5(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public IASTNode deepCopy() {
    return new StorageClassSpecifier5(cloneProperties(),firstToken,lastToken);
  }
  public ASTStringNode getTypedef() {
    return ((PropertyOne<ASTStringNode>)getProperty("typedef")).getValue();
  }
}
