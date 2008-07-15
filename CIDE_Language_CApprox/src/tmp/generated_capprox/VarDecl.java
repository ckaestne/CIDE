package tmp.generated_capprox;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class VarDecl extends GenASTNode {
  public VarDecl(ArrayList<VarDeclTokenOrComma> varDeclTokenOrComma, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyZeroOrMore<VarDeclTokenOrComma>("varDeclTokenOrComma", varDeclTokenOrComma)
    }, firstToken, lastToken);
  }
  public VarDecl(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public IASTNode deepCopy() {
    return new VarDecl(cloneProperties(),firstToken,lastToken);
  }
  public ArrayList<VarDeclTokenOrComma> getVarDeclTokenOrComma() {
    return ((PropertyZeroOrMore<VarDeclTokenOrComma>)getProperty("varDeclTokenOrComma")).getValue();
  }
}
