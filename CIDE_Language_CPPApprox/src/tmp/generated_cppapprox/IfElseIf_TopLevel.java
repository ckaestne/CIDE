package tmp.generated_cppapprox;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class IfElseIf_TopLevel extends GenASTNode {
  public IfElseIf_TopLevel(IfElseIf ifElseIf, ASTStringNode findlineend, Sequence_CodeUnit_TopLevel sequence_CodeUnit_TopLevel, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyOne<IfElseIf>("ifElseIf", ifElseIf),
      new PropertyOne<ASTStringNode>("findlineend", findlineend),
      new PropertyOne<Sequence_CodeUnit_TopLevel>("sequence_CodeUnit_TopLevel", sequence_CodeUnit_TopLevel)
    }, firstToken, lastToken);
  }
  public IfElseIf_TopLevel(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public IASTNode deepCopy() {
    return new IfElseIf_TopLevel(cloneProperties(),firstToken,lastToken);
  }
  public IfElseIf getIfElseIf() {
    return ((PropertyOne<IfElseIf>)getProperty("ifElseIf")).getValue();
  }
  public ASTStringNode getFindlineend() {
    return ((PropertyOne<ASTStringNode>)getProperty("findlineend")).getValue();
  }
  public Sequence_CodeUnit_TopLevel getSequence_CodeUnit_TopLevel() {
    return ((PropertyOne<Sequence_CodeUnit_TopLevel>)getProperty("sequence_CodeUnit_TopLevel")).getValue();
  }
}
