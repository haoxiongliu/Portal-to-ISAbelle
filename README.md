# PISA (Portal to ISAbelle)

This is a modified version of the original [PISA](https://github.com/albertqjiang/Portal-to-ISAbelle) that accodomates for [ProofAug](https://github.com/haoxiongliu/ProofAug).

PISA supports automated proof search with the interactive theorem prover [Isabelle](https://isabelle.in.tum.de).

## Installation
1. **Scala configuration**
   
    Install SDKMAN
    ```shell
    sudo apt update
    sudo apt install zip curl
    curl -s "https://get.sdkman.io" | bash
    source ~/.bashrc
    ```
    Try
    ```shell
    sdk help
    ```
    to makes ure sdk is properly installed.
    
    Install JAVA 11 and sbt
    ```shell
    sdk install java 11.0.11-open
    sdk install sbt
    ```
2. **Clone project and make sure it compiles**

   ```shell
   git clone --single-branch --branch dev_lhx git@github.com:haoxiongliu/Portal-to-ISAbelle.git
   ```

   Then compile the project:
   ```shell
   sbt compile
   sbt assembly # for java
   ```
   
3. **Configure Isabelle (parallel with 2)**

    Go back to home directory first and download isabelle2022
    ```bash
    cd ~ 
    wget https://isabelle.in.tum.de/website-Isabelle2022/dist/Isabelle2022_linux.tar.gz
    tar -xzf Isabelle2022_linux.tar.gz
    ```
    
    To add an Interactive environment
   ```bash
   cat > ~/Isabelle2022/src/HOL/Examples/InterComplex.thy <<EOF
   theory InterComplex
   imports HOL.HOL Complex_Main "HOL-Library.Code_Target_Numeral" "HOL-Library.Sum_of_Squares" "HOL-Computational_Algebra.Computational_Algebra" "HOL-Number_Theory.Number_Theory"
   begin

   end
   EOF
   ```


4. **Build Isabelle HOL**
   
   To build with 30 parallel processes:
   ```shell
   alias isabelle=~/Isabelle2022/bin/isabelle
   isabelle build -b -D Isabelle2022/src/HOL/ -j 20
   ```
   This takes ~8 hours of CPU time. The actual time depends on the number of CPUs you have. 

# Acknowledgement
This library is heavily based on [scala-isabelle](https://github.com/dominique-unruh/scala-isabelle), the work of Dominique Unruh. 

# Citation
If you use this repo, please cite the following paper 
```bibtex
@article{jiang2021lisa,
  title={LISA: Language models of ISAbelle proofs},
  author={Jiang, Albert Q. and Li, Wenda and Han, Jesse Michael and Wu, Yuhuai},
  year={2021},
  journal={6th Conference on Artificial Intelligence and Theorem Proving},
}
```
