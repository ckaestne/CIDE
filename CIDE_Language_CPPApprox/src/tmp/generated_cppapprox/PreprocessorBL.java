package tmp.generated_cppapprox;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class PreprocessorBL extends CodeUnit_InBlock {
  public PreprocessorBL(PPOtherIgnore pPOtherIgnore, ASTStringNode findlineend, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyOne<PPOtherIgnore>("pPOtherIgnore", pPOtherIgnore),
      new PropertyOne<ASTStringNode>("findlineend", findlineend)
    }, firstToken, lastToken);
  }
  public PreprocessorBL(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public IASTNode deepCopy() {
    return new PreprocessorBL(cloneProperties(),firstToken,lastToken);
  }
  public PPOtherIgnore getPPOtherIgnore() {
    return ((PropertyOne<PPOtherIgnore>)getProperty("pPOtherIgnore")).getValue();
  }
  public ASTStringNode getFindlineend() {
    return ((PropertyOne<ASTStringNode>)getProperty("findlineend")).getValue();
  }
}
