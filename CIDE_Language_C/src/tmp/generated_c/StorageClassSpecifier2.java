package tmp.generated_c;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class StorageClassSpecifier2 extends StorageClassSpecifier {
  public StorageClassSpecifier2(ASTStringNode register, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyOne<ASTStringNode>("register", register)
    }, firstToken, lastToken);
  }
  public StorageClassSpecifier2(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public IASTNode deepCopy() {
    return new StorageClassSpecifier2(cloneProperties(),firstToken,lastToken);
  }
  public ASTStringNode getRegister() {
    return ((PropertyOne<ASTStringNode>)getProperty("register")).getValue();
  }
}
