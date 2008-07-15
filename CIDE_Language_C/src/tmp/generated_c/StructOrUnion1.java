package tmp.generated_c;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class StructOrUnion1 extends StructOrUnion {
  public StructOrUnion1(ASTStringNode struct, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyOne<ASTStringNode>("struct", struct)
    }, firstToken, lastToken);
  }
  public StructOrUnion1(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public IASTNode deepCopy() {
    return new StructOrUnion1(cloneProperties(),firstToken,lastToken);
  }
  public ASTStringNode getStruct() {
    return ((PropertyOne<ASTStringNode>)getProperty("struct")).getValue();
  }
}
