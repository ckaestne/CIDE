package tmp.generated_c;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class StorageClassSpecifier1 extends StorageClassSpecifier {
  public StorageClassSpecifier1(ASTStringNode auto, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyOne<ASTStringNode>("auto", auto)
    }, firstToken, lastToken);
  }
  public StorageClassSpecifier1(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public IASTNode deepCopy() {
    return new StorageClassSpecifier1(cloneProperties(),firstToken,lastToken);
  }
  public ASTStringNode getAuto() {
    return ((PropertyOne<ASTStringNode>)getProperty("auto")).getValue();
  }
}
