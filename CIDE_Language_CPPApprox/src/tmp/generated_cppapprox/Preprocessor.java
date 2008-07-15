package tmp.generated_cppapprox;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class Preprocessor extends CodeUnit_TopLevel {
  public Preprocessor(PPOtherIgnore pPOtherIgnore, ASTStringNode findlineend, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyOne<PPOtherIgnore>("pPOtherIgnore", pPOtherIgnore),
      new PropertyOne<ASTStringNode>("findlineend", findlineend)
    }, firstToken, lastToken);
  }
  public Preprocessor(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public IASTNode deepCopy() {
    return new Preprocessor(cloneProperties(),firstToken,lastToken);
  }
  public PPOtherIgnore getPPOtherIgnore() {
    return ((PropertyOne<PPOtherIgnore>)getProperty("pPOtherIgnore")).getValue();
  }
  public ASTStringNode getFindlineend() {
    return ((PropertyOne<ASTStringNode>)getProperty("findlineend")).getValue();
  }
}
