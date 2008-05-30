package tmp.generated_dtd;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class AttribType1 extends AttribType {
  public AttribType1(StringType stringType, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyOne<StringType>("stringType", stringType)
    }, firstToken, lastToken);
  }
  public AttribType1(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public ASTNode deepCopy() {
    return new AttribType1(cloneProperties(),firstToken,lastToken);
  }
  public StringType getStringType() {
    return ((PropertyOne<StringType>)getProperty("stringType")).getValue();
  }
}
